package janggi.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("열(Column) 테스트")
class ColumnTest {

    @DisplayName("1에서 9 사이의 유효한 값으로 열을 생성할 수 있다.")
    @Test
    void createColumn_WithValidValue() {
        // given & when
        Column minColumn = new Column(1);
        Column maxColumn = new Column(9);

        // then
        assertAll(
                () -> assertThat(minColumn.value()).isEqualTo(1),
                () -> assertThat(maxColumn.value()).isEqualTo(9)
        );
    }

    @DisplayName("1~9 범위를 벗어난 값으로 열을 생성하면 예외가 발생한다.")
    @Test
    void createColumn_WithInvalidValue_ThrowsException() {
        // given & when & then
        assertAll(
                () -> assertThatThrownBy(() -> new Column(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다"),
                () -> assertThatThrownBy(() -> new Column(10))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다")
        );
    }
}
