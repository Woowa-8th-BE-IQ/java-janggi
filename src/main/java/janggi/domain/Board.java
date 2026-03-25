package janggi.domain;

import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> showBoard() {
        return Map.copyOf(board);
    }

    public Map<Position, Piece> move(Position from, Position to) {
        Piece startPiece = board.get(from);
        Piece endPiece = board.get(to);
        if (startPiece.isSameTeam(endPiece)) {
            throw new IllegalArgumentException("[ERROR] 같은 팀 기물이 있는 위치로는 이동할 수 없습니다.");
        }
        board.put(to, startPiece);

        return showBoard();
    }
}
