package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import janggi.domain.Team;
import janggi.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("빈 기물(EmptyPiece) 테스트")
class EmptyPieceTest {

    @DisplayName("빈 기물은 한나라, 초나라 어떠한 기물과 비교해도 같은 팀이 아니라고(false) 반환한다.")
    @Test
    void isSameTeam_AlwaysReturnsFalse() {
        // given
        EmptyPiece emptyPiece = new EmptyPiece();
        General hanGeneral = new General(Team.HAN);
        General choGeneral = new General(Team.CHO);

        // when & then
        assertAll(
                () -> assertThat(emptyPiece.isSameTeam(hanGeneral)).isFalse(),
                () -> assertThat(emptyPiece.isSameTeam(choGeneral)).isFalse()
        );
    }

    @DisplayName("빈 기물은 특정 팀(한/초)에 소속되어 있지 않다.")
    @Test
    void isSame_Team_AlwaysReturnsFalse() {
        // given
        EmptyPiece emptyPiece = new EmptyPiece();

        // when & then
        assertAll(
                () -> assertThat(emptyPiece.isSame(Team.HAN)).isFalse(),
                () -> assertThat(emptyPiece.isSame(Team.CHO)).isFalse()
        );
    }

    @DisplayName("빈 칸(빈 기물)에 이동 경로를 요청하면 선택된 기물이 없다는 예외가 발생한다.")
    @Test
    void getPath_ThrowsException() {
        // given
        EmptyPiece emptyPiece = new EmptyPiece();

        // when & then
        assertThatThrownBy(() -> emptyPiece.getPath(Position.from("11"), Position.from("22")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택된 기물이 없습니다.");
    }
}
