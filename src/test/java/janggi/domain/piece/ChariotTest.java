package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
import org.junit.jupiter.api.Test;

public class ChariotTest {

    @Test
    void 같은_팀의_차이면_참을_반환한다() {
        Chariot hanChariot1 = new Chariot(Team.HAN);
        Chariot hanChariot2 = new Chariot(Team.HAN);

        boolean result = hanChariot1.isSameTeam(hanChariot2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_차이면_참을_반환한다() {
        Chariot hanChariot = new Chariot(Team.HAN);
        Chariot choChariot = new Chariot(Team.CHO);

        boolean result = hanChariot.isSameTeam(choChariot);

        assertThat(result).isFalse();
    }

    @Test
    void 차의_이름은_차로_표현된다() {
        Chariot chariot = new Chariot(Team.HAN);

        String displayName = chariot.getDisplayName();
        assertThat(displayName).isEqualTo("차");
    }
}
