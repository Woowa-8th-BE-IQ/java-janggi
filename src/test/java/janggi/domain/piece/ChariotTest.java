package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import janggi.domain.Position;
import janggi.domain.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    void 한_방향으로_된_좌표로_경로를_요청하면_경로를_반환한다() {
        Chariot chariot = new Chariot(Team.HAN);

        List<Position> path = chariot.getPath(Position.from("22"), Position.from("26"));

        assertThat(path).containsExactly(
                Position.from("23"),
                Position.from("24"),
                Position.from("25"));
    }

    @Test
    void 차를_대각선으로_이동시키면_예외가_발생한다() {
        Chariot chariot = new Chariot(Team.HAN);

        assertThatThrownBy(() -> chariot.getPath(Position.from("22"), Position.from("33")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 차는 직선으로만 이동할 수 있습니다.");
    }

    @Test
    void 차를_여러_방향으로_이동시키면_예외가_발생한다() {
        Chariot chariot = new Chariot(Team.HAN);

        assertThatThrownBy(() -> chariot.getPath(Position.from("22"), Position.from("48")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 차는 직선으로만 이동할 수 있습니다.");
    }
}
