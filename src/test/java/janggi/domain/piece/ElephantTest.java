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

@DisplayName("상(Elephant) 기물 테스트")
class ElephantTest {

    @DisplayName("같은 팀의 기물인지 확인하면 올바른 결과를 반환한다.")
    @Test
    void isSameTeam() {
        // given
        Elephant hanElephant = new Elephant(Team.HAN);
        Elephant sameTeamElephant = new Elephant(Team.HAN);
        Elephant diffTeamElephant = new Elephant(Team.CHO);

        // when & then
        assertAll(
                () -> assertThat(hanElephant.isSameTeam(sameTeamElephant)).isTrue(),
                () -> assertThat(hanElephant.isSameTeam(diffTeamElephant)).isFalse()
        );
    }

    @DisplayName("직선 1칸 후 대각선 2칸(상밭)으로 이동시키면 통과하는 경로(멱) 2개를 반환한다.")
    @Test
    void getPath_ValidMove() {
        // given
        Elephant elephant = new Elephant(Team.HAN);
        Position from = Position.from("13");
        Position to = Position.from("45");
        Position expectedPath1 = Position.from("23");
        Position expectedPath2 = Position.from("34");

        // when
        List<Position> path = elephant.getPath(from, to);

        // then
        assertThat(path).containsExactly(expectedPath1, expectedPath2);
    }

    @DisplayName("상의 고유한 경로(상밭)가 아닌 곳으로 이동시키려 하면 예외가 발생한다.")
    @Test
    void getPath_InvalidMove_ThrowsException() {
        // given
        Elephant elephant = new Elephant(Team.HAN);
        Position from1 = Position.from("35");
        Position invalidTo1 = Position.from("65");

        Position from2 = Position.from("11");
        Position invalidTo2 = Position.from("33");

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> elephant.getPath(from1, invalidTo1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 상은 해당 경로로 이동할 수 없습니다."),
                () -> assertThatThrownBy(() -> elephant.getPath(from2, invalidTo2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 상은 해당 경로로 이동할 수 없습니다.")
        );
    }

    @DisplayName("상의 이동 경로(멱)에 하나라도 기물이 존재하여 길이 막히면 예외가 발생한다.")
    @Test
    void canMove_PathBlocked_ThrowsException() {
        // given
        Elephant elephant = new Elephant(Team.HAN);
<<<<<<< HEAD
        List<Piece> blockedPath = List.of(new EmptyPiece(), new Soldier(Team.HAN)); // 두 번째 멱이 막힘
        Piece targetPiece = new EmptyPiece();

        // when & then
        assertThatThrownBy(() -> elephant.canMove(blockedPath, targetPiece))
=======
        PiecesOnPath blockedPiecesOnPath = new PiecesOnPath(List.of(new EmptyPiece(), new Soldier(Team.HAN)));
        Piece targetPiece = new EmptyPiece();

        assertThatThrownBy(() -> elephant.canMove(blockedPiecesOnPath, targetPiece))
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상의 이동 경로에 기물이 있을 수 없습니다.");
    }

    @DisplayName("도착 위치에 아군 기물이 있으면 이동할 수 없고 예외가 발생한다.")
    @Test
    void canMove_TargetIsSameTeam_ThrowsException() {
        // given
        Elephant elephant = new Elephant(Team.HAN);
<<<<<<< HEAD
        List<Piece> clearPath = List.of(new EmptyPiece(), new EmptyPiece());
        Piece sameTeamTarget = new Soldier(Team.HAN);

        // when & then
        assertThatThrownBy(() -> elephant.canMove(clearPath, sameTeamTarget))
=======
        PiecesOnPath clearPiecesOnPath = new PiecesOnPath(List.of(new EmptyPiece(), new EmptyPiece()));
        Piece sameTeamTarget = new Soldier(Team.HAN);

        assertThatThrownBy(() -> elephant.canMove(clearPiecesOnPath, sameTeamTarget))
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자신의 기물로 이동할 수 없습니다.");
    }

    @DisplayName("경로가 뚫려 있고 도착 위치에 적군 기물이 있으면 정상적으로 이동 가능하다.")
    @Test
    void canMove_ValidPathAndTarget_DoesNotThrow() {
        // given
        Elephant elephant = new Elephant(Team.HAN);
<<<<<<< HEAD
        List<Piece> clearPath = List.of(new EmptyPiece(), new EmptyPiece());
=======
        PiecesOnPath clearPiecesOnPath = new PiecesOnPath(List.of(new EmptyPiece(), new EmptyPiece()));
>>>>>>> 3ddd4f93 (refactor: Path → PiecesOnPath로 rename (경로 위 기물 상태 의도 명확화))
        Piece diffTeamTarget = new Chariot(Team.CHO);

        // when & then
        assertThatNoException()
                .isThrownBy(() -> elephant.canMove(clearPiecesOnPath, diffTeamTarget));
    }
}
