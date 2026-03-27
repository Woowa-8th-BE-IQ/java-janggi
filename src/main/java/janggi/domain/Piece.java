package janggi.domain;

import janggi.domain.position.Position;
import java.util.List;

public interface Piece {

    String SAME_TEAM_ERROR_MESSAGE = "[ERROR] 자신의 기물로 이동할 수 없습니다.";

    boolean isEmptyPiece();
    boolean isCannon();
    boolean isSameTeam(Piece piece);
    boolean isSame(Team team);
    String getDisplayName();
    List<Position> getPath(Position from, Position to);
    boolean canMove(List<Piece> piecesOnPath, Piece endPiece);

}
