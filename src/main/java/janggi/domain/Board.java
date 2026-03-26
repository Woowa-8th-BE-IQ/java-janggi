package janggi.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> move(Position from, Position to) {
        Piece fromPiece = board.get(from);
        List<Position> path = fromPiece.getPath(from, to);
        List<Piece> pieceOnPath = new ArrayList<>();
        for (Position position : path) {
            pieceOnPath.add(board.get(position));
        }
        if (fromPiece.canMove(pieceOnPath, board.get(to))) {
            Piece removePiece = board.remove(from);
            board.put(to, removePiece);
        }
        return showBoard();
    }

    public Map<Position, Piece> showBoard() {
        return Map.copyOf(board);
    }
}
