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
        if (this == Team.CHO) return Team.HAN;
        throw new IllegalStateException("[ERROR] 팀 전환이 불가능한 상태입니다.");
    }

    public String getDisplayName() {
        return displayName;
    }
}
