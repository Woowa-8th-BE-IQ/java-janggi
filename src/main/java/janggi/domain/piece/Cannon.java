package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Team;
import java.util.Objects;

public class Cannon implements Piece {

    private final Team team;

    public Cannon(Team team) {
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
        return "포";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cannon cannon = (Cannon) o;
        return team == cannon.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
