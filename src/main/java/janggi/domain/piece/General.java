package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Team;

public class General implements Piece {

    private final Team team;

    public General(Team team) {
        this.team = team;
    }

    @Override
    public boolean isSameTeam(Piece other) {
        return other.isSame(team);
    }

    @Override
    public boolean isSame(Team team) {
        return this.team == team;
    }

    @Override
    public String getDisplayName() {
        return "장";
    }
}
