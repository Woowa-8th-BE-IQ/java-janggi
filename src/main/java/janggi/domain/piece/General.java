package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.position.Position;
import janggi.domain.Team;
import java.util.List;
import java.util.Objects;

public class General implements Piece {

    private final Team team;

    public General(Team team) {
        this.team = team;
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public boolean isCannon() {
        return false;
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
            throw new IllegalArgumentException(SAME_TEAM_ERROR_MESSAGE);
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
        General general = (General) o;
        return team == general.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    private void validateDiagonalMove(int diffRowAbs, int diffColumnAbs) {
        if (diffRowAbs > 0 && diffColumnAbs > 0) {
            throw new IllegalArgumentException("[ERROR] 장은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateMoveMoreThanOnce(int diffRowAbs, int diffColumnAbs) {
        if (diffRowAbs > 1 || diffColumnAbs > 1) {
            throw new IllegalArgumentException("[ERROR] 장은 해당 위치로 이동할 수 없습니다.");
        }
    }
}
