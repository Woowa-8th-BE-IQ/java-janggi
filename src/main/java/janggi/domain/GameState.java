package janggi.domain;

public enum GameState {
    PLAYING,
    FINISHED,
    ;

    public boolean isFinished() {
        return this == FINISHED;
    }
}
