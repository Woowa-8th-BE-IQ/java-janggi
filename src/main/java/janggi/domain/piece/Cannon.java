package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Direction;
import janggi.domain.position.Palace;
import janggi.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class Cannon extends AbstractPiece {

    private static final PieceType PIECE_TYPE = PieceType.CANNON;
    private static final int JUMP_PIECE_COUNT = 1;

    public Cannon(Team team) {
        super(team);
    }

    @Override
    public PieceType getType() {
        return PIECE_TYPE;
    }

    @Override
    public boolean isSameType(PieceType type) {
        return PIECE_TYPE == type;
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        if (Palace.isDiagonalMove(from, to)) {
            return Palace.getDiagonalPath(from, to);
        }
        validateStraightMove(from, to);
        return findPath(from, to);
    }

    @Override
<<<<<<< HEAD
    public void canMove(List<Piece> piecesOnPath, Piece endPiece) {
=======
    public void canMove(PiecesOnPath piecesOnPath, Piece endPiece) {
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
        validateJumpOnlyOnePiece(piecesOnPath);
        validateJumpCannon(piecesOnPath);
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

    private void validateStraightMove(Position from, Position to) {
        if (!from.hasOnlyStraightMove(to)) {
            throw new IllegalArgumentException("[ERROR] 포는 직선으로만 이동할 수 있습니다.");
        }
    }

    private void validateEndCannon(Piece endPiece) {
        if (isSamePiece(endPiece)) {
            throw new IllegalArgumentException("[ERROR] 포는 포를 잡을 수 없습니다.");
        }
    }

<<<<<<< HEAD
    private void validateJumpCannon(List<Piece> piecesOnPath) {
        if (piecesOnPath.stream().anyMatch(this::isSamePiece)) {
=======
    private void validateJumpCannon(PiecesOnPath piecesOnPath) {
        if (piecesOnPath.containsType(PieceType.CANNON)) {
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
            throw new IllegalArgumentException("[ERROR] 포는 포를 뛰어넘을 수 없습니다.");
        }
    }

<<<<<<< HEAD
    private void validateJumpOnlyOnePiece(List<Piece> piecesOnPath) {
        if (piecesOnPath.stream()
                .filter(piece -> !piece.isEmptyPiece())
                .count() != JUMP_PIECE_COUNT) {
=======
    private void validateJumpOnlyOnePiece(PiecesOnPath piecesOnPath) {
        if (!piecesOnPath.hasExactlyOneNonEmpty()) {
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
            throw new IllegalArgumentException("[ERROR] 포는 오직 1개의 기물만 뛰어넘고 이동할 수 있습니다.");
        }
    }
}
