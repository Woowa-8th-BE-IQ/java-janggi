package janggi.domain;

public enum PieceType {
    CHARIOT("차", "CHA"),
    HORSE("마", "HOR"),
    ELEPHANT("상", "ELE"),
    GUARD("사", "GRD"),
    GENERAL("장", "GEN"),
    CANNON("포", "CAN"),
    SOLDIER("졸", "SOL"),
    EMPTY("빈", "");

    private final String domainName;
    private final String displayLabel;

    PieceType(String domainName, String displayLabel) {
        this.domainName = domainName;
        this.displayLabel = displayLabel;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }
}
