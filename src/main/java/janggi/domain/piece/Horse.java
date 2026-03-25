package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Team;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Horse horse = (Horse) o;
        return team == horse.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
