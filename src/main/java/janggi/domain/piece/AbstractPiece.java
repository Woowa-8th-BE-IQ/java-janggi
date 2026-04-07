package janggi.domain.piece;

import janggi.domain.Team;

public abstract class AbstractPiece implements Piece {

    private final Team team;
    private final PieceType pieceType;

    protected AbstractPiece(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public boolean isSamePiece(Piece other) {
        return other.isSameType(pieceType);
    }

    @Override
    public boolean isSameTeam(Piece other) {
        return other.isSame(team);
    }

    @Override
    public boolean isSame(Team team) {
        return this.team == team;
    }

    @Override
    public PieceType getType() {
        return pieceType;
    }

    @Override
    public boolean isSameType(PieceType type) {
        return this.pieceType == type;
    }

    protected void validateSameTeam(Piece endPiece) {
        if (isSameTeam(endPiece)) {
            throw new IllegalArgumentException("[ERROR] 자신의 기물로 이동할 수 없습니다.");
        }
    }
}
