package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;

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


}
