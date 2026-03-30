package janggi.domain.board;

import static java.util.stream.Collectors.toList;

import janggi.domain.piece.EmptyPiece;
import janggi.domain.piece.Piece;
import janggi.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> move(Position from, Position to) {
        validateNotSamePosition(from, to);
        Piece fromPiece = board.get(from);
        List<Piece> piecesOnPath = collectPiecesOnPath(fromPiece.getPath(from, to));
        fromPiece.canMove(piecesOnPath, board.get(to));
        movePiece(from, to, fromPiece);
        return showBoard();
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

    private void validateNotSamePosition(Position from, Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표와 도착 좌표는 같을 수 없습니다.");
        }
    }
}
