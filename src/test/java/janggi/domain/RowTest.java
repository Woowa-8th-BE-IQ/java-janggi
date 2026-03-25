package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class RowTest {

    @Test
    void 열좌표_1로_열을_생성하면_좌표값_1을_가지고있다() {
        Row row = new Row(1);
        int rowValue = row.getValue();

        assertThat(rowValue).isEqualTo(1);
    }

    @Test
    void 열좌표_9로_열을_생성하면_좌표값_9을_가지고있다() {
        Row row = new Row(9);
        int rowValue = row.getValue();

        assertThat(rowValue).isEqualTo(9);
    }

    @Test
    void 범위_1에서9_사이가_아닌_열좌표로_열을_생성하면_에러가_발생한다() {

        assertAll(
                () -> assertThatThrownBy(() -> new Row(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다"),
                () -> assertThatThrownBy(() -> new Row(10))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다")
        );
    }
}
