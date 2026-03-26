package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.Position;
import janggi.domain.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Horse implements Piece {

    private final Team team;

    public Horse(Team team) {
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
        return "마";
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        if (!from.hasOffsetPairs(to, 1, 2)) {
            throw new IllegalArgumentException("[ERROR] 마는 해당 경로로 이동할 수 없습니다.");
        }

        List<Position> path = new ArrayList<>();
        path.add(from.moveStraight(to));
        return path;
    }

    @Override
    public boolean canMove(List<Piece> piecesOnPath, Piece endPiece) {
        validateAllPieceEmpty(piecesOnPath);
        return false;
    }

    private void validateAllPieceEmpty(List<Piece> piecesOnPath) {
        if (!piecesOnPath.stream().allMatch(Piece::isEmptyPiece)) {
            throw new IllegalArgumentException("[ERROR] 마의 이동 경로에 기물이 있을 수 없습니다.");
        }
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
