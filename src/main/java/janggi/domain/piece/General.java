package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;

public class General extends AbstractPiece {

    public General(Team team) {
        super(team, PieceType.GENERAL);
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
