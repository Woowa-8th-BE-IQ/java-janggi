package janggi.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import janggi.domain.Team;
import janggi.domain.piece.Piece;
import janggi.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("장기판(Board) 기물 이동 테스트")
class BoardTest {

    @DisplayName("기물을 이동하면 도착 좌표에 기존 출발 좌표의 기물이 위치한다.")
    @Test
    void movePiece_UpdatesTargetPosition() {
        // given
        Board board = BoardFactory.create("4", "4");
        Position from = Position.from("25");
        Position to = Position.from("35");
        Piece movingPiece = board.showBoard().get(from);

        // when
        Map<Position, Piece> movedBoard = board.move(from, to, Team.HAN);

        // then
        assertThat(movedBoard).containsEntry(to, movingPiece);
    }

    @DisplayName("기물을 이동하면 기존 출발 좌표는 빈 기물 상태가 된다.")
    @Test
    void movePiece_EmptiesOriginalPosition() {
        // given
        Board board = BoardFactory.create("4", "4");
        Position from = Position.from("25");
        Position to = Position.from("35");

        // when
        Map<Position, Piece> movedBoard = board.move(from, to, Team.HAN);

        // then
        assertThat(movedBoard.get(from).isEmptyPiece()).isTrue();
    }

    @DisplayName("출발 좌표와 도착 좌표가 동일하면 예외가 발생한다.")
    @Test
    void movePiece_SameSourceAndTarget_ThrowsException() {
        // given
        Board board = BoardFactory.create("4", "4");
        Position position = Position.from("11");

        // when & then
        assertThatThrownBy(() -> board.move(position, position, Team.HAN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 출발 좌표와 도착 좌표는 같을 수 없습니다.");
    }

    @DisplayName("유효하지 않은 형태의 좌표로 이동하려 하면 예외가 발생한다.")
    @Test
    void movePiece_InvalidPosition_ThrowsException() {
        // given
        Board board = BoardFactory.create("4", "4");
        Position validTarget = Position.from("11");

        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> board.move(Position.from("101"), validTarget, Team.HAN))
                        .hasMessage("[ERROR] 좌표값 입력은 2자리 숫자여야 합니다."),
                () -> assertThatThrownBy(() -> board.move(Position.from("10"), validTarget, Team.HAN))
                        .hasMessage("[ERROR] 열 좌표는 1~9까지 사용 가능 합니다"),
                () -> assertThatThrownBy(() -> board.move(Position.from("1a"), validTarget, Team.HAN))
                        .hasMessage("[ERROR] 좌표값은 숫자여야 합니다.")
        );
    }

    @DisplayName("현재 턴이 아닌 상대팀의 기물을 이동시키려 하면 예외가 발생한다.")
    @Test
    void movePiece_WrongTurn_ThrowsException() {
        // given
        Board board = BoardFactory.create("4", "4");
        Position hanPiecePosition = Position.from("25");
        Position targetPosition = Position.from("35");

        // when & then (초나라 턴일 때 한나라 기물 조작 시도)
        assertThatThrownBy(() -> board.move(hanPiecePosition, targetPosition, Team.CHO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 현재 턴의 기물만 이동할 수 있습니다.");
    }
}
