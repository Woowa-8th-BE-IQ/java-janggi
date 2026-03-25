package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    void 문자열_35로_위치를_생성하면_좌표_3_5를_가지고_있다() {
        Position position = Position.from("35");

        String result = position.toString();

        assertThat(result).isEqualTo("3,5");
    }

    @Test
    void 문자열_05로_위치를_생성하면_좌표_10_5를_가지고_있다() {
        Position position = Position.from("05");

        String result = position.toString();

        assertThat(result).isEqualTo("10,5");
    }

    @Test
    void 두_자리_숫자가_아닌_문자열로_위치를_생성하면_예외가_발생한다() {
        assertThatThrownBy(
                () -> Position.from("105"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 좌표값 입력은 2자리 숫자여야 합니다.");
    }

    @Test
    void 존재하지_않는_좌표로_위치를_생성하면_예외가_발생한다() {
        assertThatThrownBy(
                () -> Position.from("00"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 행 좌표는 1~9까지 사용 가능 합니다");
    }

    @Test
    void 동일한_좌표를_가진_위치_객체는_동등하다() {
       Position position1 = Position.from("12");
       Position position2 = Position.from("12");

       assertThat(position1).isEqualTo(position2);
    }
}
