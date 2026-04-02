package janggi.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("위치(Position) 테스트")
class PositionTest {

    @DisplayName("2자리 문자열 숫자로 위치 객체를 생성하면 올바른 좌표를 반환한다.")
    @Test
    void createPosition_FromValidString() {
        // given & when
        Position pos1 = Position.from("35");
        Position pos2 = Position.from("05");

        // then
        assertThat(pos1.toString()).isEqualTo("3,5");
        assertThat(pos2.toString()).isEqualTo("10,5");
    }

    @DisplayName("두 자리 숫자가 아닌 문자열을 입력하면 예외가 발생한다.")
    @Test
    void createPosition_WithInvalidLength_ThrowsException() {
        // given
        String invalidInput = "105";

        // when & then
        assertThatThrownBy(() -> Position.from(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 좌표값 입력은 2자리 숫자여야 합니다.");
    }

    @DisplayName("존재하지 않는 열/행의 좌표로 위치를 생성하면 예외가 발생한다.")
    @Test
    void createPosition_WithOutOfRange_ThrowsException() {
        // given
        String outOfRangeInput = "00";

        // when & then
        assertThatThrownBy(() -> Position.from(outOfRangeInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다");
    }

    @DisplayName("동일한 좌표를 가진 위치 객체는 서로 동등하다.")
    @Test
    void positionsWithSameCoordinates_AreEqual() {
        // given
        Position position1 = Position.from("12");
        Position position2 = Position.from("12");

        // when & then
        assertThat(position1).isEqualTo(position2);
    }
}
