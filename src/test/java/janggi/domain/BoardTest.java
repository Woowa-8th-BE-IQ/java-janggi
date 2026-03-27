package janggi.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void 출발_좌표와_도착_좌표가_같으면_예외가_발생한다() {
        Board board = new Board(BoardFactory.create("4", "4"));

        assertThatThrownBy(() -> board.move(Position.from("11"), Position.from("11")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출발 좌표와 도착 좌표는 같을 수 없습니다.");
    }

    @Test
    void 잘못된_좌표로_출발_및_도착_좌표를_입력하면_예외가_발생한다() {
        Board board = new Board(BoardFactory.create("4", "4"));

        assertAll(
                () -> assertThatThrownBy(() -> board.move(Position.from("101"), Position.from("11")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 좌표값 입력은 2자리 숫자여야 합니다."),
                () -> assertThatThrownBy(() -> board.move(Position.from("10"), Position.from("11")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다"),
                () -> assertThatThrownBy(() -> board.move(Position.from("1a"), Position.from("11")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다"),
                () -> assertThatThrownBy(() -> board.move(Position.from("a0"), Position.from("11")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 행 좌표는 1~10까지 사용 가능 합니다")
        );
    }
}
