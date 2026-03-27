package janggi.domain.position;

import java.util.Objects;

public class Position {

    private static final int LENGTH_OF_POSITION_FORMAT = 2;
    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 1;

    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position from(String rowColumn) {
        validatePositionLength(rowColumn);
        return new Position(new Row(extractRowValue(rowColumn)), new Column(extractColumnValue(rowColumn)));
    }

    private static int extractRowValue(String rowColumn) {
        int rowValue = rowColumn.charAt(ROW_INDEX) - '0';
        if (rowValue == 0) {
            rowValue += 10;
        }
        return rowValue;
    }

    private static int extractColumnValue(String rowColumn) {
        return rowColumn.charAt(COLUMN_INDEX) - '0';
    }

    private static void validatePositionLength(String rowColumn) {
        if (rowColumn.length() != LENGTH_OF_POSITION_FORMAT) {
            throw new IllegalArgumentException("[ERROR] 좌표값 입력은 2자리 숫자여야 합니다.");
        }
    }

    public boolean hasOffsetPairs(Position other, int value1, int value2) {
        int diffRowAbs = Math.abs(other.getRowValue() - this.getRowValue());
        int diffColumnAbs = Math.abs(other.getColumnValue() - this.getColumnValue());

        return (diffRowAbs == value1 && diffColumnAbs == value2) || (diffColumnAbs == value1 && diffRowAbs == value2);
    }

    public boolean hasOnlyStraightMove(Position to) {
        int diffRowAbs = Math.abs(to.getRowValue() - this.getRowValue());
        int diffColumnAbs = Math.abs(to.getColumnValue() - this.getColumnValue());

        if ((diffRowAbs == 0 && diffColumnAbs > 0) || (diffRowAbs > 0 && diffColumnAbs == 0)) {
            return true;
        }
        return false;
    }

    public Position moveStraight(Position to) {
        int rowValue = getRowValue();
        int columnValue = getColumnValue();
        int diffRow = to.getRowValue() - rowValue;
        int diffColumn = to.getColumnValue() - columnValue;

        if (diffRow > diffColumn) {
            int newRow = rowValue + (diffRow / Math.abs(diffRow));
            return Position.from("" + newRow + columnValue);
        }

        int newColumn = columnValue + (diffColumn / Math.abs(diffColumn));
        return Position.from("" + rowValue + newColumn);
    }

    public Position moveDiagonal(Position to) {
        int rowValue = getRowValue();
        int columnValue = getColumnValue();
        int diffRow = to.getRowValue() - rowValue;
        int diffColumn = to.getColumnValue() - columnValue;
        int diffRowAbs = Math.abs(diffRow);
        int diffColumnAbs = Math.abs(diffColumn);

        int unitRow = diffRow / diffRowAbs;
        int unitColumn = diffColumn / diffColumnAbs;

        return Position.from("" + (rowValue + unitRow) + (columnValue + unitColumn));
    }

    public int getRowValue() {
        return row.getValue();
    }

    public int getColumnValue() {
        return column.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return Objects.equals(row, position.row) && Objects.equals(column, position.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return row.getValue() + "," + column.getValue();
    }
}
