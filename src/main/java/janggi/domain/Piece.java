package janggi.domain;

public interface Piece {
    boolean isSameTeam(Piece piece);
    boolean isSame(Team team);
    String getDisplayName();
    void canMove(Delta delta);
}
