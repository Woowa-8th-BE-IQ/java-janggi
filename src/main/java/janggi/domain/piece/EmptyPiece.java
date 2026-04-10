package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;

public class EmptyPiece implements Piece {

    private static final PieceType PIECE_TYPE = PieceType.EMPTY;

    @Override
    public boolean isEmptyPiece() {
        return true;
    }

    @Override
    public boolean isSamePiece(Piece other) {
        return other.isSameType(PIECE_TYPE);
    }

    @Override
    public boolean isSameType(PieceType type) {
        return PIECE_TYPE == type;
    }

    @Override
    public PieceType getType() {
        return PIECE_TYPE;
    }

    @Override
    public boolean isSameTeam(Piece other) {
        return false;
    }

    @Override
    public boolean isSame(Team team) {
        return false;
    }

    @Override
    public List<Position> getPath(Position from, Position to) {
        throw new IllegalArgumentException("[ERROR] 선택된 기물이 없습니다.");
    }

    @Override
<<<<<<< HEAD
    public void canMove(List<Piece> piecesOnPath, Piece endPiece) {
=======
    public void canMove(PiecesOnPath piecesOnPath, Piece endPiece) {
        // 빈 칸은 이동할 수 없으므로 호출되지 않음
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
    }
}
