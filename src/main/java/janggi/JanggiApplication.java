package janggi;

import janggi.infrastructure.GameRepositoryImpl;
import janggi.view.InputView;

public class JanggiApplication {

    public static void main(String[] args) {
        GameRepositoryImpl gameRepository = new GameRepositoryImpl();
        String mode = InputView.readGameMode();
        JanggiGame game;
        if (mode.equals("1")) {
            game = JanggiGame.initialize(gameRepository);
        } else {
            game = JanggiGame.load(gameRepository);
        }
        game.start();
    }
}
