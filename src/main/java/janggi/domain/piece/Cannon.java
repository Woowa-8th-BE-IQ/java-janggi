package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Position;
import janggi.domain.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cannon implements Piece {

    private final Team team;

    public Cannon(Team team) {
        this.team = team;
    }

    @Override
    public boolean isEmptyPiece() {
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
        return "포";
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        if (!from.hasOnlyStraightMove(to)) {
            throw new IllegalArgumentException("[ERROR] 포는 직선으로만 이동할 수 있습니다.");
        }

        List<Position> path = new ArrayList<>();
        Position target = from.moveStraight(to);
        while (target.hasOnlyStraightMove(to)) {
            path.add(target);
            target = target.moveStraight(to);
        }
        return path;
    }

    @Override
    public boolean canMove(List<Piece> piecesOnPath, Piece endPiece) {
        return false;
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
