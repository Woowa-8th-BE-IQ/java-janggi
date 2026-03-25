package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Team;

public class Elephant implements Piece {

    private final Team team;

    public Elephant(Team team) {
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
        return "상";
    }
}
