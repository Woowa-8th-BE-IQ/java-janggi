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

@DisplayName("차(Chariot) 기물 테스트")
class ChariotTest {

    @DisplayName("같은 팀의 기물인지 확인하면 올바른 결과를 반환한다.")
    @Test
    void isSameTeam() {
        // given
        Chariot hanChariot = new Chariot(Team.HAN);
        Chariot sameTeamChariot = new Chariot(Team.HAN);
        Chariot diffTeamChariot = new Chariot(Team.CHO);

        // when & then
        assertAll(
                () -> assertThat(hanChariot.isSameTeam(sameTeamChariot)).isTrue(),
                () -> assertThat(hanChariot.isSameTeam(diffTeamChariot)).isFalse()
        );
    }

    @DisplayName("직선 방향으로 이동시키면 통과하는 모든 경로 좌표를 반환한다.")
    @Test
    void getPath_ValidStraightMove() {
        // given
        Chariot chariot = new Chariot(Team.HAN);
        Position from = Position.from("22");
        Position to = Position.from("26");

        // when
        List<Position> path = chariot.getPath(from, to);

        // then
        assertThat(path).containsExactly(
                Position.from("23"),
                Position.from("24"),
                Position.from("25")
        );
    }

    @DisplayName("대각선이나 여러 방향이 섞인 경로 등 직선이 아닌 곳으로 이동시키려 하면 예외가 발생한다.")
    @Test
    void getPath_NotStraightMove_ThrowsException() {
        // given
        Chariot chariot = new Chariot(Team.HAN);
        Position from = Position.from("22");
        Position invalidTo1 = Position.from("33");
        Position invalidTo2 = Position.from("48");

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> chariot.getPath(from, invalidTo1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 차는 직선으로만 이동할 수 있습니다."),
                () -> assertThatThrownBy(() -> chariot.getPath(from, invalidTo2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 차는 직선으로만 이동할 수 있습니다.")
        );
    }

    @DisplayName("차의 이동 경로에 빈 기물이 아닌 기물이 존재하면 건너뛸 수 없어 예외가 발생한다.")
    @Test
    void canMove_PathBlocked_ThrowsException() {
        // given
        Chariot chariot = new Chariot(Team.HAN);
        List<Piece> blockedPath = List.of(new Soldier(Team.HAN));
        Piece targetPiece = new EmptyPiece();

        // when & then
        assertThatThrownBy(() -> chariot.canMove(blockedPath, targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 차의 이동 경로에 기물이 있을 수 없습니다.");
    }

    @DisplayName("도착 위치에 아군 기물이 있으면 이동할 수 없고 예외가 발생한다.")
    @Test
    void canMove_TargetIsSameTeam_ThrowsException() {
        // given
        Chariot chariot = new Chariot(Team.HAN);
        List<Piece> clearPath = List.of(new EmptyPiece());
        Piece sameTeamTarget = new Soldier(Team.HAN);

        // when & then
        assertThatThrownBy(() -> chariot.canMove(clearPath, sameTeamTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자신의 기물로 이동할 수 없습니다.");
    }

    @DisplayName("경로가 뚫려 있고 도착 위치에 적군 기물이 있으면 정상적으로 이동 가능하다.")
    @Test
    void canMove_ValidPathAndTarget_DoesNotThrow() {
        // given
        Chariot chariot = new Chariot(Team.HAN);
        List<Piece> clearPath = List.of(new EmptyPiece(), new EmptyPiece());
        Piece diffTeamTarget = new Chariot(Team.CHO);

        // when & then
        assertThatNoException()
                .isThrownBy(() -> chariot.canMove(clearPath, diffTeamTarget));
    }
}
