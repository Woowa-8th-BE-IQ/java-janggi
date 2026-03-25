package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
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
    void 장의_이름은_장로_표현된다() {
        General general = new General(Team.HAN);

        String displayName = general.getDisplayName();
        assertThat(displayName).isEqualTo("장");
    }
}
