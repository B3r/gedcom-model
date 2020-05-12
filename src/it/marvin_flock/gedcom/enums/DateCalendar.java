package it.marvin_flock.gedcom.enums;

public enum DateCalendar {
    DHEBREW,
    DROMAN,
    DFRENCH,
    DGREGORIAN,
    DJULIAN,
    DUNKNOWN;

    @Override
    public String toString() {
        if (name().equals("DFRENCH")) {
            return "@#" + name() + " R@";
        }
        return "@#" + name() + "@";
    }
}
