package janggi.domain;

import java.util.List;

public interface Piece {
    boolean isSameTeam(Piece piece);
    boolean isSame(Team team);
    String getDisplayName();
    List<Position> getPath(Position from, Position to);
    boolean canMove(List<Piece> piecesOnPath, Piece endPiece);
}
