package it.marvin_flock.gedcom.enums;

public enum SpouseSealingStatus {
    CANCELED,
    CLEARED,
    COMPLETED,
    DNS,
    DNSCAN,
    PRE1970,
    QUALIFIED,
    SUBMITTED,
    UNCLEARED;

    @Override
    public String toString() {
        if (name().equals("PRE1970")) {
            return "PRE-1970";
        }
        if (name().equals("DNSCAN")) {
            return "DNS/CAN";
        }
        return name();
    }

}
