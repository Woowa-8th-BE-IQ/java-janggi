package janggi.domain.piece;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;

public interface Piece {
    boolean isEmptyPiece();
    boolean isSamePiece(Piece other);
    boolean isSameType(PieceType pieceType);
    PieceType getType();
    boolean isSameTeam(Piece other);
    boolean isSame(Team team);
    List<Position> getPath(Position from, Position to);
<<<<<<< HEAD
    void canMove(List<Piece> piecesOnPath, Piece endPiece);
=======
    void canMove(PiecesOnPath piecesOnPath, Piece endPiece);
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
}
