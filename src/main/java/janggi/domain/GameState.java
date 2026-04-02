package janggi.domain;

public enum GameState {
    PLAYING,
    FINISHED,
    ;

    public boolean isPlaying() {
        return this == PLAYING;
    }
}
