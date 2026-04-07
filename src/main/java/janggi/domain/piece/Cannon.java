package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Direction;
import janggi.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Cannon extends AbstractPiece {

    public Cannon(Team team) {
        super(team, PieceType.CANNON);
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        validateMove(from, to);
        return findPath(from, to);
    }

    @Override
    public void canMove(Path path, Piece endPiece) {
        validateJumpOnlyOnePiece(path);
        validateJumpCannon(path);
        validateSameTeam(endPiece);
        validateEndCannon(endPiece);
    }

    private List<Position> findPath(Position from, Position to) {
        List<Position> path = new ArrayList<>();
        Direction direction = Direction.straightBetween(from, to);
        Position target = from.move(direction);
        while (target.hasOnlyStraightMove(to)) {
            path.add(target);
            target = target.move(direction);
        }
        return path;
    }

    private void validateMove(Position from, Position to) {
        if (!from.hasOnlyStraightMove(to)) {
            throw new IllegalArgumentException("[ERROR] 포는 직선으로만 이동할 수 있습니다.");
        }
    }

    private void validateEndCannon(Piece endPiece) {
        if (isSamePiece(endPiece)) {
            throw new IllegalArgumentException("[ERROR] 포는 포를 잡을 수 없습니다.");
        }
    }

    private void validateJumpCannon(Path path) {
        if (path.containsType(PieceType.CANNON)) {
            throw new IllegalArgumentException("[ERROR] 포는 포를 뛰어넘을 수 없습니다.");
        }
    }

    private void validateJumpOnlyOnePiece(Path path) {
        if (!path.hasExactlyOneNonEmpty()) {
            throw new IllegalArgumentException("[ERROR] 포는 오직 1개의 기물만 뛰어넘고 이동할 수 있습니다.");
        }
    }
}
