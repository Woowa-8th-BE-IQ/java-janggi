package janggi.domain.piece;

import janggi.domain.Delta;
import janggi.domain.Piece;
import janggi.domain.Team;
import java.util.Objects;

public class General implements Piece {

    private final Team team;

    @Override
    public void canMove(Delta delta) {
        if (isNotOnlyOneStraightMove(delta)) {
            throw new IllegalArgumentException("[ERROR] 장은 두 칸 이상 이동할 수 없습니다.");
        }
    }

    private boolean isNotOnlyOneStraightMove(Delta delta) {
        return !((Math.abs(delta.getDiffRow()) == 1 && Math.abs(delta.getDiffColumn()) == 0) ||
                (Math.abs(delta.getDiffRow()) == 0 && Math.abs(delta.getDiffColumn()) == 1));
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        General general = (General) o;
        return team == general.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
