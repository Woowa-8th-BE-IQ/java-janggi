package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
    void 문자열_09로_위치를_생성하면_좌표_10_9를_가지고_있다() {
        Position position = Position.from("09");

        String result = position.toString();

        assertThat(result).isEqualTo("10,9");
    }
}
