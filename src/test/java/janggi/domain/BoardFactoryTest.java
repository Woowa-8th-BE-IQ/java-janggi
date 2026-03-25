package janggi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import janggi.domain.piece.Cannon;
import janggi.domain.piece.Chariot;
import janggi.domain.piece.Elephant;
import janggi.domain.piece.General;
import janggi.domain.piece.Guard;
import janggi.domain.piece.Horse;
import janggi.domain.piece.Soldier;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class BoardFactoryTest {

    @Test
    void 초기화된_보드의_기물_배치를_확인한다() {
        Map<Position, Piece> boardState = BoardFactory.create();

        assertAll(
                // 한나라
                () -> assertThat(boardState).containsEntry(Position.from("11"), new Chariot(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("12"), new Elephant(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("13"), new Horse(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("14"), new Guard(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("16"), new Guard(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("17"), new Horse(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("18"), new Elephant(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("19"), new Chariot(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("25"), new General(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("32"), new Cannon(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("38"), new Cannon(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("41"), new Soldier(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("43"), new Soldier(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("45"), new Soldier(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("47"), new Soldier(Team.HAN)),
                () -> assertThat(boardState).containsEntry(Position.from("49"), new Soldier(Team.HAN)),
                // 초나라
                () -> assertThat(boardState).containsEntry(Position.from("01"), new Chariot(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("02"), new Elephant(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("03"), new Horse(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("04"), new Guard(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("06"), new Guard(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("07"), new Horse(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("08"), new Elephant(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("09"), new Chariot(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("95"), new General(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("82"), new Cannon(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("88"), new Cannon(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("71"), new Soldier(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("73"), new Soldier(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("75"), new Soldier(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("77"), new Soldier(Team.CHO)),
                () -> assertThat(boardState).containsEntry(Position.from("79"), new Soldier(Team.CHO))
        );
    }
}
