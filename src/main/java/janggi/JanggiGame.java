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

    private final InputView inputView;
    private final OutputView outputView;
    private Board board;

    public JanggiGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        initializeBoard();
        outputView.printBoard(board.showBoard());
        play();
    }

    private void initializeBoard() {
        String hanSetup = inputView.readHanSetup();
        String choSetup = inputView.readChosetup();
        board = BoardFactory.create(hanSetup, choSetup);
    }

    private void play() {
        Team currentTeam = Team.CHO;
        while (true) {
            String input = inputView.readPosition(currentTeam.getDisplayName());
            if (input.equals(END_COMMAND)) {
                outputView.printGameEnd();
                return;
            }
            GameState state = processTurn(input, currentTeam);
            if (state.isFinished()) {
                return;
            }
            currentTeam = currentTeam.convert();
        }
    }

    private GameState processTurn(String input, Team currentTeam) {
        try {
            Map<Position, Piece> updatedBoard = movePiece(input, currentTeam);
            if (isGeneralCaptured(updatedBoard)) {
                outputView.printBoard(updatedBoard);
                outputView.printWinner(currentTeam);
                return GameState.FINISHED;
            }
            outputView.printBoard(updatedBoard);
            return GameState.PLAYING;
        } catch (IllegalArgumentException exception) {
            outputView.printError(exception.getMessage());
            return GameState.PLAYING;
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
