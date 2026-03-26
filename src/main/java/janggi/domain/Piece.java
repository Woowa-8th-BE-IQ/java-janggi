package janggi.domain;

import java.util.List;

public interface Piece {

    String SAME_TEAM_ERROR_MESSAGE = "[ERROR] 같은 팀의 기물이 있는 곳으로는 이동할 수 없습니다.";

    boolean isSameTeam(Piece piece);
    boolean isSame(Team team);
    String getDisplayName();
    List<Position> getPath(Position from, Position to);
    boolean canMove(List<Piece> piecesOnPath, Piece endPiece);
}
