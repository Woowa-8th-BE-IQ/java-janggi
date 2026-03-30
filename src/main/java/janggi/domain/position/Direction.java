package janggi.domain.position;

public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1),
    NORTH_EAST(-1, 1),
    NORTH_WEST(-1, -1),
    SOUTH_EAST(1, 1),
    SOUTH_WEST(1, -1);

    private final int rowOffset;
    private final int colOffset;

    Direction(int rowOffset, int colOffset) {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }

    public Position next(Position position) {
        return Position.of(position.getRowValue() + rowOffset,
                position.getColumnValue() + colOffset);
    }

    public static Direction straightBetween(Position from, Position to) {
        int diffRow = to.getRowValue() - from.getRowValue();
        int diffColumn = to.getColumnValue() - from.getColumnValue();

        if (Math.abs(diffRow) > Math.abs(diffColumn)) {
            return findByOffset(toUnit(diffRow), 0);
        }
        return findByOffset(0, toUnit(diffColumn));
    }

    public static Direction diagonalBetween(Position from, Position to) {
        int diffRow = to.getRowValue() - from.getRowValue();
        int diffColumn = to.getColumnValue() - from.getColumnValue();

        return findByOffset(toUnit(diffRow), toUnit(diffColumn));
    }

    private static Direction findByOffset(int rowOffset, int colOffset) {
        for (Direction direction : values()) {
            if (direction.rowOffset == rowOffset && direction.colOffset == colOffset) {
                return direction;
            }
        }
        throw new IllegalArgumentException("[ERROR] 해당하는 방향이 없습니다.");
    }

    private static int toUnit(int diff) {
        if (diff == 0) return 0;
        return diff / Math.abs(diff);
    }
}
