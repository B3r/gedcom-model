package it.marvin_flock.gedcom.enums;

public enum ChildSealingStatus {
    BIC,
    CLEARED,
    COMPLETED,
    DNS,
    PRE1970,
    QUALIFIED,
    STILLBORN,
    SUBMITTED,
    UNCLEARED;

    @Override
    public String toString() {
        if (name().equals("PRE1970")) {
            return "PRE-1970";
        }
        return name();
    }
}
