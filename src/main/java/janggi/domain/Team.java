package janggi.domain;

public enum Team {
    HAN("한"),
    CHO("초"),
    ;

    private final String displayName;

    Team(String displayName) {
        this.displayName = displayName;
    }

    public Team convert() {
        if (this == Team.HAN) return Team.CHO;
        return Team.HAN;
    }

    public String getDisplayName() {
        return displayName;
    }
}
