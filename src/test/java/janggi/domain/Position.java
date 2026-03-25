package janggi.domain;

public class Position {

    private final Row row;
    private final Column column;

    private Position(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public static Position from(String rowColumn) {
        String[] split = rowColumn.split("");
        int rowValue = Integer.parseInt(split[0]);
        int colValue = Integer.parseInt(split[1]);

        if (rowValue == 0) {
            rowValue += 10;
        }

        return new Position(new Row(rowValue), new Column(colValue));
    }

    @Override
    public String toString() {
        return row.getValue() + "," + column.getValue();
    }
}
