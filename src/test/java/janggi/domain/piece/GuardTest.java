package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
import org.junit.jupiter.api.Test;

public class GuardTest {

    @Test
    void 같은_팀의_사이면_참을_반환한다() {
        Guard hanGuard1 = new Guard(Team.HAN);
        Guard hanGuard2 = new Guard(Team.HAN);

        boolean result = hanGuard1.isSameTeam(hanGuard2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_사이면_참을_반환한다() {
        Guard hanGuard = new Guard(Team.HAN);
        Guard choGuard = new Guard(Team.CHO);

        boolean result = hanGuard.isSameTeam(choGuard);

        assertThat(result).isFalse();
    }

    @Test
    void 사의_이름은_사로_표현된다() {
        Guard guard = new Guard(Team.HAN);

        String displayName = guard.getDisplayName();
        assertThat(displayName).isEqualTo("사");
    }
}
