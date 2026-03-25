package janggi.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import janggi.domain.Team;
import org.junit.jupiter.api.Test;

public class EmptyPieceTest {

    @Test
    void 두_나라의_기물과_같은_팀인지_확인하면_거짓을_반환한다() {
        EmptyPiece emptyPiece = new EmptyPiece();
        General hanGeneral = new General(Team.HAN);
        General choGeneral = new General(Team.CHO);

        boolean hanResult = emptyPiece.isSameTeam(hanGeneral);
        boolean choResult = emptyPiece.isSameTeam(choGeneral);

        assertAll(
                () -> assertThat(hanResult).isFalse(),
                () -> assertThat(choResult).isFalse()
        );
    }

    @Test
    void 빈_기물은_두_나라에_소속되지_않는다() {
        EmptyPiece emptyPiece = new EmptyPiece();

        boolean hanResult = emptyPiece.isSame(Team.HAN);
        boolean choResult = emptyPiece.isSame(Team.CHO);

        assertAll(
                () -> assertThat(hanResult).isFalse(),
                () -> assertThat(choResult).isFalse()
        );
    }

    @Test
    void 빈기물의_이름은_빈으로_표현된다() {
        EmptyPiece emptyPiece = new EmptyPiece();

        String displayName = emptyPiece.getDisplayName();
        assertThat(displayName).isEqualTo("빈");
    }
}
