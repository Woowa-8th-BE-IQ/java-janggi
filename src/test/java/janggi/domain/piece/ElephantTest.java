package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
import org.junit.jupiter.api.Test;

public class ElephantTest {

    @Test
    void 같은_팀의_상이면_참을_반환한다() {
        Elephant hanElephant1 = new Elephant(Team.HAN);
        Elephant hanElephant2 = new Elephant(Team.HAN);

        boolean result = hanElephant1.isSameTeam(hanElephant2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_상이면_참을_반환한다() {
        Elephant hanElephant = new Elephant(Team.HAN);
        Elephant choElephant = new Elephant(Team.CHO);

        boolean result = hanElephant.isSameTeam(choElephant);

        assertThat(result).isFalse();
    }

    @Test
    void 상의_이름은_상로_표현된다() {
        Elephant elephant = new Elephant(Team.HAN);

        String displayName = elephant.getDisplayName();
        assertThat(displayName).isEqualTo("상");
    }
}
