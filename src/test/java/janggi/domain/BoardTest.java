package janggi.domain;

import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void 보드를_반환한다() {
        Board board = new Board(BoardFactory.create("4", "4"));

    }
}
