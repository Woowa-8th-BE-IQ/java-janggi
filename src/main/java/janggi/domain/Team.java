package janggi.domain;

public enum Team {
    HAN("한"),
    CHO("초"),
    NONE("무"),
    ;

    private final String displayName;

    Team(String displayName) {
        this.displayName = displayName;
    }

    public Team convert() {
        if (this == Team.HAN) return Team.CHO;
        if (this == Team.CHO) return Team.HAN;
        return Team.NONE;
    }

    public String getDisplayName() {
        return displayName;
    }
}
