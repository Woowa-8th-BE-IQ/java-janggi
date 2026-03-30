package janggi.domain;

import janggi.domain.position.Position;
import java.util.List;

public interface Piece {
    boolean isEmptyPiece();
    boolean isSamePiece(Piece other);
    boolean isSameType(PieceType pieceType);
    PieceType getType();
    boolean isSameTeam(Piece other);
    boolean isSame(Team team);
    List<Position> getPath(Position from, Position to);
    boolean canMove(List<Piece> piecesOnPath, Piece endPiece);
}
