package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Team;

public class Horse implements Piece {

    private final Team team;

    public Horse(Team team) {
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
        return "마";
    }
}
