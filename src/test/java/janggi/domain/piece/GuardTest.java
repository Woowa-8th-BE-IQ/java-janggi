package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import janggi.domain.Team;
import janggi.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("사(Guard) 기물 테스트")
class GuardTest {

    @DisplayName("같은 팀의 기물인지 확인하면 올바른 결과를 반환한다.")
    @Test
    void isSameTeam() {
        // given
        Guard hanGuard = new Guard(Team.HAN);
        Guard sameTeamGuard = new Guard(Team.HAN);
        Guard diffTeamGuard = new Guard(Team.CHO);

        // when & then
        assertAll(
                () -> assertThat(hanGuard.isSameTeam(sameTeamGuard)).isTrue(),
                () -> assertThat(hanGuard.isSameTeam(diffTeamGuard)).isFalse()
        );
    }

    @DisplayName("직선으로 1칸 이동시키면 정상적으로 경로를 반환한다.")
    @Test
    void getPath_ValidMove() {
        // given
        Guard guard = new Guard(Team.HAN);

        // when
        List<Position> path = guard.getPath(Position.from("11"), Position.from("12"));

        // then
        assertThat(path).isEmpty();
    }

    @DisplayName("대각선이나 1칸을 초과하여 이동시키려 하면 예외가 발생한다.")
    @Test
    void getPath_InvalidMove_ThrowsException() {
        // given
        Guard guard = new Guard(Team.HAN);

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> guard.getPath(Position.from("11"), Position.from("22")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 사는 해당 위치로 이동할 수 없습니다."),
                () -> assertThatThrownBy(() -> guard.getPath(Position.from("11"), Position.from("15")))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 사는 해당 위치로 이동할 수 없습니다.")
        );
    }

    @DisplayName("도착 위치에 아군 기물이 있으면 이동할 수 없고 예외가 발생한다.")
    @Test
    void canMove_TargetIsSameTeam_ThrowsException() {
        // given
        Guard guard = new Guard(Team.HAN);

        // when & then
        assertThatThrownBy(() -> guard.canMove(List.of(), new Chariot(Team.HAN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자신의 기물로 이동할 수 없습니다.");
    }

    @DisplayName("도착 위치에 적군 기물이 있으면 정상적으로 이동(공격) 가능하다.")
    @Test
    void canMove_TargetIsDiffTeam_DoesNotThrow() {
        // given
        Guard guard = new Guard(Team.HAN);

        // when & then
        assertThatNoException()
                .isThrownBy(() -> guard.canMove(List.of(), new Chariot(Team.CHO)));
    }
}
