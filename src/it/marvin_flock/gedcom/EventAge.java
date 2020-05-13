package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.dates.Date;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.Period;
@Getter
public class EventAge extends GedcomElement {
    private final Date date;

    private final boolean isYounger;
    private final boolean isOlder;
    private final boolean isUnborn;

    public EventAge(@NonNull Date date, boolean isYounger, boolean isOlder, boolean isUnborn) {
        this.date = date;
        this.isYounger = isYounger;
        this.isOlder = isOlder;
        this.isUnborn = isUnborn;
    }

    public String toString(LocalDate compareDate, final int level) {
        if (compareDate == null || date == null) {
            throw new NullPointerException("dates may not be empty");
        }
        if (!date.isComplete()) {
            return "";
        }

        return level +
                " AGE " +
                getString(compareDate) +
                fullStop;
    }

    protected String getString(LocalDate compareDate) {
        String value;
        if (isUnborn) {
            value = "UNBORN";
        } else {
            Period period = Period.between(date.asDate(), compareDate);
            if (period.isNegative()) {
                throw new IllegalArgumentException("date of event can not be before birth");
            }
            if (period.getYears() < 1) {
                value = "INFANT";
            } else if (period.getYears() < 8) {
                value = "CHILD";
            } else {
                String olderPrefix = isOlder ? "> " : "";
                String prefix = isYounger ? "< " : olderPrefix;
                value = buildPeriodString(period);
                value = prefix + value;
            }
        }
        return value;
    }

    private String buildPeriodString(Period period) {
        StringBuilder sb = new StringBuilder();
        if (period.getYears() > 0) {
            sb.append(period.getYears());
            sb.append("y ");
        }
        if (period.getMonths() > 0) {
            sb.append(period.getMonths());
            sb.append("m ");
        }
        if (period.getDays() > 0) {
            sb.append(period.getDays());
            sb.append("d");
        }
        return sb.toString();
    }

    @Override
    public String toString(int level) {
        return "AGE NEEDS A COMPARE DATE";
    }
}
