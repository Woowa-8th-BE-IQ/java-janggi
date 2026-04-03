package janggi.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import janggi.domain.Team;
import janggi.domain.piece.Cannon;
import janggi.domain.piece.Chariot;
import janggi.domain.piece.Elephant;
import janggi.domain.piece.General;
import janggi.domain.piece.Guard;
import janggi.domain.piece.Horse;
import janggi.domain.piece.Piece;
import janggi.domain.piece.Soldier;
import janggi.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("장기판 생성(BoardFactory) 테스트")
class BoardFactoryTest {

    @DisplayName("한나라 기물 차림 번호가 4(바깥상)일 때, 전체 초기 기물이 올바르게 배치된다.")
    @Test
    void createBoard_HanOuterElephantFormation() {
        // given
        Board board = BoardFactory.create("4", "4");

        // when
        Map<Position, Piece> boardState = board.showBoard();

        // then
        assertPiece(boardState, "11", new Chariot(Team.HAN),  Team.HAN);
        assertPiece(boardState, "12", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "13", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "14", new Guard(Team.HAN),    Team.HAN);
        assertPiece(boardState, "16", new Guard(Team.HAN),    Team.HAN);
        assertPiece(boardState, "17", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "18", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "19", new Chariot(Team.HAN),  Team.HAN);
        assertPiece(boardState, "25", new General(Team.HAN),  Team.HAN);
        assertPiece(boardState, "32", new Cannon(Team.HAN),   Team.HAN);
        assertPiece(boardState, "38", new Cannon(Team.HAN),   Team.HAN);
        assertPiece(boardState, "41", new Soldier(Team.HAN),  Team.HAN);
        assertPiece(boardState, "43", new Soldier(Team.HAN),  Team.HAN);
        assertPiece(boardState, "45", new Soldier(Team.HAN),  Team.HAN);
        assertPiece(boardState, "47", new Soldier(Team.HAN),  Team.HAN);
        assertPiece(boardState, "49", new Soldier(Team.HAN),  Team.HAN);
    }

    @DisplayName("초나라 기물 차림 번호가 4(바깥상)일 때, 전체 초기 기물이 올바르게 배치된다.")
    @Test
    void createBoard_ChoOuterElephantFormation() {
        // given
        Board board = BoardFactory.create("4", "4");

        // when
        Map<Position, Piece> boardState = board.showBoard();

        // then
        assertPiece(boardState, "01", new Chariot(Team.CHO),  Team.CHO);
        assertPiece(boardState, "02", new Elephant(Team.CHO), Team.CHO);
        assertPiece(boardState, "03", new Horse(Team.CHO),    Team.CHO);
        assertPiece(boardState, "04", new Guard(Team.CHO),    Team.CHO);
        assertPiece(boardState, "06", new Guard(Team.CHO),    Team.CHO);
        assertPiece(boardState, "07", new Horse(Team.CHO),    Team.CHO);
        assertPiece(boardState, "08", new Elephant(Team.CHO), Team.CHO);
        assertPiece(boardState, "09", new Chariot(Team.CHO),  Team.CHO);
        assertPiece(boardState, "95", new General(Team.CHO),  Team.CHO);
        assertPiece(boardState, "82", new Cannon(Team.CHO),   Team.CHO);
        assertPiece(boardState, "88", new Cannon(Team.CHO),   Team.CHO);
        assertPiece(boardState, "71", new Soldier(Team.CHO),  Team.CHO);
        assertPiece(boardState, "73", new Soldier(Team.CHO),  Team.CHO);
        assertPiece(boardState, "75", new Soldier(Team.CHO),  Team.CHO);
        assertPiece(boardState, "77", new Soldier(Team.CHO),  Team.CHO);
        assertPiece(boardState, "79", new Soldier(Team.CHO),  Team.CHO);
    }

    @DisplayName("차림 번호 1번을 입력하면 양 팀 모두 마-상-마-상(왼상차림)으로 배치된다.")
    @Test
    void createBoard_Formation1_LeftElephant() {
        // given
        Board board = BoardFactory.create("1", "1");

        // when
        Map<Position, Piece> boardState = board.showBoard();

        // then
        assertPiece(boardState, "12", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "13", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "02", new Elephant(Team.CHO), Team.CHO);
        assertPiece(boardState, "03", new Horse(Team.CHO),    Team.CHO);
    }

    @DisplayName("차림 번호 2번을 입력하면 양 팀 모두 상-마-상-마(오른상차림)으로 배치된다.")
    @Test
    void createBoard_Formation2_RightElephant() {
        // given
        Board board = BoardFactory.create("2", "2");

        // when
        Map<Position, Piece> boardState = board.showBoard();

        // then
        assertPiece(boardState, "12", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "13", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "02", new Horse(Team.CHO),    Team.CHO);
        assertPiece(boardState, "03", new Elephant(Team.CHO), Team.CHO);
    }

    @DisplayName("차림 번호 3번을 입력하면 양 팀 모두 마-상-상-마(안상차림)으로 배치된다.")
    @Test
    void createBoard_Formation3_InnerElephant() {
        // given
        Board board = BoardFactory.create("3", "3");

        // when
        Map<Position, Piece> boardState = board.showBoard();

        // then
        assertPiece(boardState, "12", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "13", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "17", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "18", new Horse(Team.HAN),    Team.HAN);
    }

    @DisplayName("차림 번호 4번을 입력하면 양 팀 모두 상-마-마-상(바깥상차림)으로 배치된다.")
    @Test
    void createBoard_Formation4_OuterElephant() {
        // given
        Board board = BoardFactory.create("4", "4");

        // when
        Map<Position, Piece> boardState = board.showBoard();

        // then
        assertPiece(boardState, "12", new Elephant(Team.HAN), Team.HAN);
        assertPiece(boardState, "13", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "17", new Horse(Team.HAN),    Team.HAN);
        assertPiece(boardState, "18", new Elephant(Team.HAN), Team.HAN);
    }

    private void assertPiece(Map<Position, Piece> board, String pos, Piece expected, Team team) {
        Piece actual = board.get(Position.from(pos));
        assertThat(actual.isSamePiece(expected)).isTrue();
        assertThat(actual.isSame(team)).isTrue();
    }
}
