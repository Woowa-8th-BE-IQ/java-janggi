package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Position;
import janggi.domain.Team;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HorseTest {

    @Test
    void 같은_팀의_마이면_참을_반환한다() {
        Horse hanHorse1 = new Horse(Team.HAN);
        Horse hanHorse2 = new Horse(Team.HAN);

        boolean result = hanHorse1.isSameTeam(hanHorse2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_마이면_참을_반환한다() {
        Horse hanHorse = new Horse(Team.HAN);
        Horse choHorse = new Horse(Team.CHO);

        boolean result = hanHorse.isSameTeam(choHorse);

        assertThat(result).isFalse();
    }

    @Test
    void 마의_이름은_마로_표현된다() {
        Horse horse = new Horse(Team.HAN);

        String displayName = horse.getDisplayName();
        assertThat(displayName).isEqualTo("마");
    }

    @Test
    void 직선으로_먼저_한_칸_직선_방향의_대각선으로_한_칸_이동시키면_경로를_반환한다() {
        Horse horse = new Horse(Team.HAN);

        List<Position> path = horse.getPath(Position.from("36"), Position.from("57"));

        assertThat(path).containsExactly(Position.from("46"));
    }
}
