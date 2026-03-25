package janggi.domain;

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
        int columnValue = rowColumn.charAt(COLUMN_INDEX) - '0';
        return columnValue;
    }

    private static void validatePositionLength(String rowColumn) {
        if (rowColumn.length() != LENGTH_OF_POSITION_FORMAT) {
            throw new IllegalArgumentException("[ERROR] 좌표값 입력은 2자리 숫자여야 합니다.");
        }
    }

    @Override
    public String toString() {
        return row.getValue() + "," + column.getValue();
    }
}
