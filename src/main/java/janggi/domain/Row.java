package janggi.domain;

public class Row {

    public static final int ROW_LOWER_THRESH_HOLD = 1;
    public static final int ROW_UPPER_THRESH_HOLD = 10;

    private final int value;

    public Row(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (value < ROW_LOWER_THRESH_HOLD || value > ROW_UPPER_THRESH_HOLD) {
            throw new IllegalArgumentException("[ERROR] 열 좌표는 1~10까지 사용 가능 합니다");
        }
    }

    public int getValue() {
        return value;
    }
}
