package janggi.domain.board;

import static java.util.stream.Collectors.toList;

import janggi.domain.Team;
import janggi.domain.piece.EmptyPiece;
import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceType;
import janggi.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Board {

    private static final long GENERAL_COUNT = 2;

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> move(Position from, Position to, Team currentTeam) {
        validate(from, to, currentTeam);
        Piece fromPiece = board.get(from);
        List<Piece> piecesOnPath = collectPiecesOnPath(fromPiece.getPath(from, to));
        fromPiece.canMove(piecesOnPath, board.get(to));
        movePiece(from, to, fromPiece);
        return showBoard();
    }

    public boolean isGeneralCaptured() {
        return board.values().stream()
                .filter(piece -> !piece.isEmptyPiece())
                .filter(piece -> piece.isSameType(PieceType.GENERAL))
                .count() < GENERAL_COUNT;
    }

    private List<Piece> collectPiecesOnPath(List<Position> path) {
        return path.stream()
                .map(board::get)
                .collect(toList());
    }

    private void movePiece(Position from, Position to, Piece piece) {
        board.put(from, new EmptyPiece());
        board.put(to, piece);
    }

    public Map<Position, Piece> showBoard() {
        return Map.copyOf(board);
    }

    private void validate(Position from, Position to, Team currentTeam) {
        validateNotSamePosition(from, to);
        validateNotEmpty(from);
        validateTurn(from, currentTeam);
    }

    private void validateNotSamePosition(Position from, Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표와 도착 좌표는 같을 수 없습니다.");
        }
    }

    private void validateNotEmpty(Position from) {
        if (board.get(from).isEmptyPiece()) {
            throw new IllegalArgumentException("[ERROR] 선택된 기물이 없습니다.");
        }
    }

    private void validateTurn(Position from, Team currentTeam) {
        if (!board.get(from).isSame(currentTeam)) {
            throw new IllegalArgumentException("[ERROR] 현재 턴의 기물만 이동할 수 있습니다.");
        }
    }
}
