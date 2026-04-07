package janggi.domain.position;

import java.util.List;

public enum Palace {
    HAN(1, 3, 4, 6, 2, 5),
    CHO(8, 10, 4, 6, 9, 5),
    ;

    private final int rowMin;
    private final int rowMax;
    private final int colMin;
    private final int colMax;
    private final int centerRow;
    private final int centerCol;

    Palace(int rowMin, int rowMax, int colMin, int colMax, int centerRow, int centerCol) {
        this.rowMin = rowMin;
        this.rowMax = rowMax;
        this.colMin = colMin;
        this.colMax = colMax;
        this.centerRow = centerRow;
        this.centerCol = centerCol;
    }

    public boolean contains(Position position) {
        int row = position.getRowValue();
        int col = position.getColumnValue();
        return row >= rowMin && row <= rowMax && col >= colMin && col <= colMax;
    }

    public boolean isOnDiagonalLine(Position position) {
        if (!contains(position)) {
            return false;
        }
        int row = position.getRowValue();
        int col = position.getColumnValue();
        boolean isCenter = row == centerRow && col == centerCol;
        boolean isCorner = (row == rowMin || row == rowMax) && (col == colMin || col == colMax);
        return isCenter || isCorner;
    }

    public boolean canMoveDiagonally(Position from, Position to) {
        int distance = from.rowDistanceTo(to);
        return distance > 0
                && distance == from.colDistanceTo(to)
                && isOnDiagonalLine(from)
                && isOnDiagonalLine(to);
    }

    private List<Position> diagonalPath(Position from, Position to) {
        if (from.rowDistanceTo(to) == 1) {
            return List.of();
        }
        return List.of(Position.of(centerRow, centerCol));
    }

    public static boolean isDiagonalMove(Position from, Position to) {
        return HAN.canMoveDiagonally(from, to) || CHO.canMoveDiagonally(from, to);
    }

    public static List<Position> getDiagonalPath(Position from, Position to) {
        if (HAN.canMoveDiagonally(from, to)) {
            return HAN.diagonalPath(from, to);
        }
        return CHO.diagonalPath(from, to);
    }
}
