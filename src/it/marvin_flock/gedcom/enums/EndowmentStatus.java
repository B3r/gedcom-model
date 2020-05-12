package it.marvin_flock.gedcom.enums;

public enum EndowmentStatus {
    CHILD,
    CLEARED,
    COMPLETED,
    INFANT,
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
