package janggi.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void 출발_좌표와_도착_좌표가_같으면_예외가_발생한다() {
        Board board = new Board(BoardFactory.create("4", "4"));

        assertThatThrownBy(() -> board.move(Position.from("11"), Position.from("11")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출발 좌표와 도착 좌표는 같을 수 없습니다.");
    }
}
