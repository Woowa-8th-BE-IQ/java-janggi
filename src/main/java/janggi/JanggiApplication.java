package janggi;

import janggi.view.InputView;
import janggi.view.OutputView;

public class JanggiApplication {

    public static void main(String[] args) {
        new JanggiGame(new InputView(), new OutputView()).start();
    }
}
