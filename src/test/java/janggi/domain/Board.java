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
}
