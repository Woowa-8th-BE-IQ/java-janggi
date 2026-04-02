package janggi.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("행(Row) 테스트")
class RowTest {

    @DisplayName("1에서 10 사이의 유효한 값으로 행을 생성할 수 있다.")
    @Test
    void createRow_WithValidValue() {
        // given & when
        Row minRow = new Row(1);
        Row maxRow = new Row(10);

        // then
        assertAll(
                () -> assertThat(minRow.getValue()).isEqualTo(1),
                () -> assertThat(maxRow.getValue()).isEqualTo(10)
        );
    }

    @DisplayName("1~10 범위를 벗어난 값으로 행을 생성하면 예외가 발생한다.")
    @Test
    void createRow_WithInvalidValue_ThrowsException() {
        // given & when & then
        assertAll(
                () -> assertThatThrownBy(() -> new Row(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 행 좌표는 1~10까지 사용 가능 합니다"),
                () -> assertThatThrownBy(() -> new Row(11))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 행 좌표는 1~10까지 사용 가능 합니다")
        );
    }
}
