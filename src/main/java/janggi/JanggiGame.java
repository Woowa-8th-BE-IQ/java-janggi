package janggi;

import janggi.domain.GameState;
import janggi.domain.Team;
import janggi.domain.board.Board;
import janggi.domain.board.BoardFactory;
import janggi.domain.piece.Piece;
import janggi.domain.piece.PieceType;
import janggi.domain.position.Position;
import janggi.view.InputView;
import janggi.view.OutputView;
import java.util.List;
import java.util.Map;

public class JanggiGame {

    private static final String END_COMMAND = "end";

    private Board board;

    public void start() {
        initializeBoard();
        OutputView.printBoard(board.showBoard());
        play();
    }

    private void initializeBoard() {
        String hanSetup = InputView.readHanSetup();
        String choSetup = InputView.readChosetup();
        board = BoardFactory.create(hanSetup, choSetup);
    }

    private void play() {
        Team currentTeam = Team.CHO;
        GameState state = GameState.TURN_SUCCESS;
        while (state.isPlaying()) {
            String input = InputView.readPosition(currentTeam.getDisplayName());
            state = progressTurn(input, currentTeam);
            if (state.isTurnSuccess()) {
                currentTeam = currentTeam.convert();
            }
        }
    }

    private GameState progressTurn(String input, Team currentTeam) {
        if (input.equals(END_COMMAND)) {
            OutputView.printGameEnd();
            return GameState.FINISHED;
        }
        return processTurn(input, currentTeam);
    }

    private GameState processTurn(String input, Team currentTeam) {
        try {
            Map<Position, Piece> updatedBoard = movePiece(input, currentTeam);
            if (isGeneralCaptured(updatedBoard)) {
                OutputView.printBoard(updatedBoard);
                OutputView.printWinner(currentTeam);
                return GameState.FINISHED;
            }
            OutputView.printBoard(updatedBoard);
            return GameState.TURN_SUCCESS;
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return GameState.TURN_FAILED;
        }
    }

    private Map<Position, Piece> movePiece(String input, Team currentTeam) {
        List<String> positions = List.of(input.split(" "));
        validateInputSize(positions);
        Position from = Position.from(positions.getFirst());
        Position to = Position.from(positions.getLast());
        return board.move(from, to, currentTeam);
    }

    private boolean isGeneralCaptured(Map<Position, Piece> updatedBoard) {
        return updatedBoard.values().stream()
                .filter(piece -> !piece.isEmptyPiece())
                .filter(piece -> piece.isSameType(PieceType.GENERAL))
                .count() < 2;
    }

    private void validateInputSize(List<String> positions) {
        if (positions.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 이동 좌표는 출발과 도착 2개를 입력해야 합니다.");
        }
    }
}
