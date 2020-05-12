package it.marvin_flock.gedcom.dates;

import lombok.NonNull;

public class DatePeriod implements IDate {
    private final IDate from;
    private final IDate to;

    public DatePeriod(@NonNull IDate from, @NonNull IDate to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (from != null) {
            sb.append("FROM ");
            sb.append(from.toString());
        }
        if (to != null) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append("TO ");
            sb.append(to.toString());
        }
        return sb.toString();
    }
}
