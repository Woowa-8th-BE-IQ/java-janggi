package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;

public class General extends AbstractPiece {

    private static final PieceType PIECE_TYPE = PieceType.GENERAL;

    public General(Team team) {
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
        validateMove(from, to);
        return List.of();
    }

    @Override
    public void canMove(Path path, Piece endPiece) {
        validateSameTeam(endPiece);
    }

    private void validateMove(Position from, Position to) {
        if (!from.hasDistancePair(to, 0, 1)) {
            throw new IllegalArgumentException("[ERROR] 장은 해당 위치로 이동할 수 없습니다.");
        }
    }
}
