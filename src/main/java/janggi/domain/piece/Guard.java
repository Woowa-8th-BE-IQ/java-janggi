package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Position;
import janggi.domain.Team;
import java.util.List;
import java.util.Objects;

public class Guard implements Piece {

    private final Team team;

    public Guard(Team team) {
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
        return "사";
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        int diffRowAbs = Math.abs(to.getRowValue() - from.getRowValue());
        int diffColumnAbs = Math.abs(to.getColumnValue() - from.getColumnValue());
        validateDiagonalMove(diffRowAbs, diffColumnAbs);
        validateMoveMoreThanOnce(diffRowAbs, diffColumnAbs);

        return List.of();
    }

    @Override
    public boolean canMove(List<Piece> piecesOnPath, Piece endPiece) {
        if (isSameTeam(endPiece)) {
            throw new IllegalArgumentException("[ERROR] 같은 팀의 기물이 있는 곳으로는 이동할 수 없습니다.");
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guard guard = (Guard) o;
        return team == guard.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    private void validateDiagonalMove(int diffRowAbs, int diffColumnAbs) {
        if (diffRowAbs > 0 && diffColumnAbs > 0) {
            throw new IllegalArgumentException("[ERROR] 사는 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateMoveMoreThanOnce(int diffRowAbs, int diffColumnAbs) {
        if (diffRowAbs > 1 || diffColumnAbs > 1) {
            throw new IllegalArgumentException("[ERROR] 사는 해당 위치로 이동할 수 없습니다.");
        }
    }
}
