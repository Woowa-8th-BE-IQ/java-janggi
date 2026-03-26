package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Position;
import janggi.domain.Team;
import java.util.List;
import java.util.Objects;

public class Soldier implements Piece {

    private final Team team;

    public Soldier(Team team) {
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
        return "졸";
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        return null;
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
        Soldier soldier = (Soldier) o;
        return team == soldier.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
