package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GeneralTest {

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

    @Test
    void 직선_한_칸보다_많이_이동시키면_예외를_발생한다() {
        General general = new General(Team.HAN);

        assertThatThrownBy(() -> general.getPath(Position.from("11"), Position.from("15")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 장은 해당 위치로 이동할 수 없습니다.");
    }

    @Test
    void 이동할_위치에_같은_팀이_있으면_예외가_발생한다() {
        General general = new General(Team.HAN);

        assertThatThrownBy(() -> general.canMove(List.of(), new Chariot(Team.HAN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자신의 기물로 이동할 수 없습니다.");
    }

    @Test
    void 이동할_위치에_다른_팀이_있으면_예외가_발생하지_않는다() {
        General general = new General(Team.HAN);

        assertThatNoException()
                .isThrownBy(() -> general.canMove(List.of(), new Chariot(Team.CHO)));
    }
}
