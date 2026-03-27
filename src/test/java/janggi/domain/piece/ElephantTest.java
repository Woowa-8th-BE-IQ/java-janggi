package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;
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

    @Test
    void 직선으로_먼저_한_칸_직선_방향의_대각선으로_연속_두_칸_이동시키면_경로를_반환한다() {
        Elephant elephant = new Elephant(Team.HAN);

        List<Position> path = elephant.getPath(Position.from("13"), Position.from("45"));

        assertThat(path).containsExactly(Position.from("23"), Position.from("34"));
    }

    @Test
    void 직선으로_먼저_한_칸_직선_방향의_대각선으로_연속_두_칸_이외의_경로로_이동시키면_예외가_발생한다() {
        Elephant elephant = new Elephant(Team.HAN);

        assertAll(
                () -> assertThatThrownBy(() -> elephant.getPath(Position.from("35"), Position.from("65")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 상은 해당 경로로 이동할 수 없습니다."),
                () -> assertThatThrownBy(() -> elephant.getPath(Position.from("11"), Position.from("33")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 상은 해당 경로로 이동할 수 없습니다.")
        );
    }

    @Test
    void 경로에_존재하는_기물_중_빈_기물이_아닌_기물이_있으면_예외가_발생한다() {
        Elephant elephant = new Elephant(Team.HAN);

        assertThatThrownBy(() -> elephant.canMove(List.of(new EmptyPiece(), new Soldier(Team.HAN)), new EmptyPiece()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상의 이동 경로에 기물이 있을 수 없습니다.");
    }

    @Test
    void 이동할_위치에_같은_팀이_있으면_예외가_발생한다() {
        Elephant elephant = new Elephant(Team.HAN);

        assertThatThrownBy(() -> elephant.canMove(List.of(new EmptyPiece(), new EmptyPiece()), new Soldier(Team.HAN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자신의 기물로 이동할 수 없습니다.");
    }

    @Test
    void 이동_가능_확인_성공_테스트() {
        Elephant elephant = new Elephant(Team.HAN);

        boolean result = elephant.canMove(List.of(new EmptyPiece(), new EmptyPiece()), new Chariot(Team.CHO));

        assertThat(result).isTrue();
    }
}
