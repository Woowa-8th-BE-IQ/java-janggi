package janggi.domain;

public enum Team {
    HAN,
    CHO,
    ;

    public Team convert() {
        if (this == Team.HAN) return Team.CHO;
        return Team.HAN;
    }
}
