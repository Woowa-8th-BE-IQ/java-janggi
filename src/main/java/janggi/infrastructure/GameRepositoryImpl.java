package janggi.infrastructure;

import janggi.domain.GameRepository;
import janggi.domain.Team;
import janggi.domain.board.Board;
import janggi.domain.piece.Piece;
import janggi.domain.position.Position;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {

    @Override
    public long save(Board board, Team currentTeam) {
        long gameId = saveGame(currentTeam);
        savePieces(gameId, board.showBoard());
        return gameId;
    }

    @Override
    public void update(long gameId, Board board, Team currentTeam) {
        updateTurn(gameId, currentTeam);
        updatePieces(gameId, board.showBoard());
    }

    @Override
    public Optional<Board> findBoardById(long gameId) {
        String sql = "SELECT row_pos, col_pos, team, type FROM piece WHERE janggi_game_id = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            Map<Position, Piece> pieces = mapToPieces(rs);
            if (pieces.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(new Board(pieces));
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] 보드 조회 실패", e);
        }
    }

    @Override
    public Team findTurnById(long gameId) {
        String sql = "SELECT turn FROM janggi_game WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                throw new IllegalArgumentException("[ERROR] 게임을 찾을 수 없습니다: " + gameId);
            }
            return Team.valueOf(rs.getString("turn"));
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] 턴 조회 실패", e);
        }
    }

    private long saveGame(Team currentTeam) {
        String sql = "INSERT INTO janggi_game (turn) VALUES (?)";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, currentTeam.name());
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            keys.next();
            return keys.getLong(1);
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] 게임 저장 실패", e);
        }
    }

    private void savePieces(long gameId, Map<Position, Piece> pieces) {
        String sql = "INSERT INTO piece (janggi_game_id, row_pos, col_pos, team, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
                pstmt.setLong(1, gameId);
                pstmt.setInt(2, entry.getKey().getRowValue());
                pstmt.setInt(3, entry.getKey().getColumnValue());
                pstmt.setString(4, PieceFactory.toTeamString(entry.getValue()));
                pstmt.setString(5, entry.getValue().getType().name());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] 기물 저장 실패", e);
        }
    }

    private void updateTurn(long gameId, Team currentTeam) {
        String sql = "UPDATE janggi_game SET turn = ? WHERE id = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, currentTeam.name());
            pstmt.setLong(2, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] 턴 업데이트 실패", e);
        }
    }

    private void updatePieces(long gameId, Map<Position, Piece> pieces) {
        deletePieces(gameId);
        savePieces(gameId, pieces);
    }

    private void deletePieces(long gameId) {
        String sql = "DELETE FROM piece WHERE janggi_game_id = ?";
        try (Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, gameId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("[ERROR] 기물 삭제 실패", e);
        }
    }

    private Map<Position, Piece> mapToPieces(ResultSet rs) throws SQLException {
        Map<Position, Piece> pieces = new LinkedHashMap<>();
        while (rs.next()) {
            Position position = Position.of(rs.getInt("row_pos"), rs.getInt("col_pos"));
            Piece piece = PieceFactory.create(rs.getString("team"), rs.getString("type"));
            pieces.put(position, piece);
        }
        return pieces;
    }
}
