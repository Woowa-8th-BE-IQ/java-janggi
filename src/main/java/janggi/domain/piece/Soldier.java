package janggi.domain.piece;

import janggi.domain.Piece;
import janggi.domain.PieceType;
import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;

public class Soldier extends AbstractPiece {

    private static final PieceType PIECE_TYPE = PieceType.SOLDIER;

    public Soldier(Team team) {
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
        validateBackStep(from, to);
        return List.of();
    }

    @Override
    public boolean canMove(List<Piece> piecesOnPath, Piece endPiece) {
        validateSameTeam(endPiece);
        return true;
    }

    private void validateMove(Position from, Position to) {
        if (!from.hasDistancePair(to, 0, 1)) {
            throw new IllegalArgumentException("[ERROR] 졸은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void validateBackStep(Position from, Position to) {
        if (isSame(Team.HAN) && (to.getRowValue() - from.getRowValue()) == -1) {
            throw new IllegalArgumentException("[ERROR] 졸은 뒷 방향으로 이동할 수 없습니다.");
        }
        if (isSame(Team.CHO) && (to.getRowValue() - from.getRowValue()) == 1) {
            throw new IllegalArgumentException("[ERROR] 졸은 뒷 방향으로 이동할 수 없습니다.");
        }
    }
}
