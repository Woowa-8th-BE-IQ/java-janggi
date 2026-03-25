package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
import org.junit.jupiter.api.Test;

public class CannonTest {

    @Test
    void 같은_팀의_포이면_참을_반환한다() {
        Cannon hanCannon1 = new Cannon(Team.HAN);
        Cannon hanCannon2 = new Cannon(Team.HAN);

        boolean result = hanCannon1.isSameTeam(hanCannon2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_포이면_참을_반환한다() {
        Cannon hanCannon = new Cannon(Team.HAN);
        Cannon choCannon = new Cannon(Team.CHO);

        boolean result = hanCannon.isSameTeam(choCannon);

        assertThat(result).isFalse();
    }

    @Test
    void 포의_이름은_포로_표현된다() {
        Cannon cannon = new Cannon(Team.HAN);

        String displayName = cannon.getDisplayName();
        assertThat(displayName).isEqualTo("포");
    }
}
