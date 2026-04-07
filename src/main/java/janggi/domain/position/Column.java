package janggi.domain.position;

public record Column(int value) {

    public static final int COLUMN_LOWER_THRESH_HOLD = 1;
    public static final int COLUMN_UPPER_THRESH_HOLD = 9;

    public Column {
        if (value < COLUMN_LOWER_THRESH_HOLD || value > COLUMN_UPPER_THRESH_HOLD) {
            throw new IllegalArgumentException("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다");
        }
    }
}
