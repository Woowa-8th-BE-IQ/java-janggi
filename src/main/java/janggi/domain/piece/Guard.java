package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;

public class Guard extends AbstractPiece {

    public Guard(Team team) {
        super(team, PieceType.GUARD);
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
            throw new IllegalArgumentException("[ERROR] 사는 해당 위치로 이동할 수 없습니다.");
        }
    }
}
