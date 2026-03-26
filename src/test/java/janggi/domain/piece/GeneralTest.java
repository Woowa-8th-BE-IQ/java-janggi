package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import janggi.domain.Column;
import janggi.domain.Piece;
import janggi.domain.Position;
import janggi.domain.Row;
import janggi.domain.Team;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeneralTest {

    private Map<Position, Piece> base;

    @BeforeEach
    void setup() {
        base = new LinkedHashMap<>();
        for (int row = Row.ROW_LOWER_THRESH_HOLD; row <= Row.ROW_UPPER_THRESH_HOLD; row++) {
            for (int col = Column.COLUMN_LOWER_THRESH_HOLD; col <= Column.COLUMN_UPPER_THRESH_HOLD; col++) {
                int rowInput = row;
                if (row == 10) {
                    rowInput = 0;
                }
                base.put(Position.from("" + rowInput + col), new EmptyPiece());
            }
        }
    }

    @Test
    void 같은_팀의_장이면_참을_반환한다() {
        General hanGeneral1 = new General(Team.HAN);
        General hanGeneral2 = new General(Team.HAN);

        boolean result = hanGeneral1.isSameTeam(hanGeneral2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_장이면_참을_반환한다() {
        General hanGeneral = new General(Team.HAN);
        General choGeneral = new General(Team.CHO);

        boolean result = hanGeneral.isSameTeam(choGeneral);

        assertThat(result).isFalse();
    }

    @Test
    void 장의_이름은_장로_표현된다() {
        General general = new General(Team.HAN);

        String displayName = general.getDisplayName();
        assertThat(displayName).isEqualTo("장");
    }

    @Test
    void 직선_한_칸을_이동시키면_경로를_반환한다() {
        General general = new General(Team.HAN);

        List<Position> path = general.getPath(Position.from("11"), Position.from("12"));

        assertThat(path).hasSize(0);
    }

    @Test
    void 대각선_이동시키면_예외가_발생한다() {
        General general = new General(Team.HAN);

        assertThatThrownBy(() -> general.getPath(Position.from("11"), Position.from("22")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 장은 해당 위치로 이동할 수 없습니다.");
    }
}
