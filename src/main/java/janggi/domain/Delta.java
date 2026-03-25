package janggi.domain;

public class Delta {

    private final int diffRow;
    private final int diffColumn;

    private Delta(int diffRow, int diffColumn) {
        this.diffRow = diffRow;
        this.diffColumn = diffColumn;
    }

    public int getDiffRow() {
        return diffRow;
    }

    public int getDiffColumn() {
        return diffColumn;
    }

    public static Delta of(Position from, Position to) {
        int diffRow = to.getRowValue() - from.getRowValue();
        int diffColumn = to.getColumnValue() - from.getColumnValue();
        return new Delta(diffRow, diffColumn);
    }
}
