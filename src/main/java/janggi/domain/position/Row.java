package janggi.domain.position;

public record Row(int value) {

    public static final int ROW_LOWER_THRESH_HOLD = 1;
    public static final int ROW_UPPER_THRESH_HOLD = 10;

    public Row {
        if (value < ROW_LOWER_THRESH_HOLD || value > ROW_UPPER_THRESH_HOLD) {
            throw new IllegalArgumentException("[ERROR] 행 좌표는 1~10까지 사용 가능 합니다");
        }
    }
}
