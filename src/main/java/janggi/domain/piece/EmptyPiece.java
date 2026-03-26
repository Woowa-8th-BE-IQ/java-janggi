package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Position;
import janggi.domain.Team;
import java.util.List;

public class EmptyPiece implements Piece {

    @Override
    public boolean isEmptyPiece() {
        return true;
    }

    @Override
    public boolean isCannon() {
        return false;
    }

    @Override
    public boolean isSameTeam(Piece other) {
        return false;
    }

    @Override
    public boolean isSame(Team team) {
        return false;
    }

    @Override
    public String getDisplayName() {
        return "빈";
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        return null;
    }

    @Override
    public boolean canMove(List<Piece> piecesOnPath, Piece endPiece) {
        return false;
    }
}
