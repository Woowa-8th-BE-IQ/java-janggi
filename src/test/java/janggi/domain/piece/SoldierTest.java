package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
import org.junit.jupiter.api.Test;

public class SoldierTest {

    @Test
    void 같은_팀의_졸이면_참을_반환한다() {
        Soldier hanSoldier1 = new Soldier(Team.HAN);
        Soldier hanSoldier2 = new Soldier(Team.HAN);

        boolean result = hanSoldier1.isSameTeam(hanSoldier2);

        assertThat(result).isTrue();
    }

    @Test
    void 서로_다른_팀의_졸이면_참을_반환한다() {
        Soldier hanSoldier = new Soldier(Team.HAN);
        Soldier choSoldier = new Soldier(Team.CHO);

        boolean result = hanSoldier.isSameTeam(choSoldier);

        assertThat(result).isFalse();
    }
}
