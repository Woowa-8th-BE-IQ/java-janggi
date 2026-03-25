package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Team;

public class EmptyPiece implements Piece {

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
}
