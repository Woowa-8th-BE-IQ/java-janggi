package janggi.domain;

public class Column {

    public static final int COLUMN_LOWER_THRESH_HOLD = 1;
    public static final int COLUMN_UPPER_THRESH_HOLD = 9;

    private final int value;

    public Column(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (value < COLUMN_LOWER_THRESH_HOLD || value > COLUMN_UPPER_THRESH_HOLD) {
            throw new IllegalArgumentException("[ERROR] 행 좌표는 1~9까지 사용 가능 합니다");
        }
    }

    public int getValue() {
        return value;
    }
}
