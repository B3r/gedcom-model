package it.marvin_flock.gedcom.dates;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.enums.DateCalendar;
import lombok.Getter;
import lombok.NonNull;


/**
 * Does a Julian date need a conversion to greg?
 * Implementation only for gregorian dates so far
 */
@Getter
public class DateValue extends GedcomElement {
    private DateCalendar type;
    private boolean isInterpreted;
    private IDate date;
    private String phrase;

    public DateValue(DateCalendar type, IDate date, String phrase, boolean isInterpreted) {
        if (type != null && type != DateCalendar.DGREGORIAN) {
            throw new IllegalStateException("current type is not supported: " + type.toString());
        }
        if (date == null && phrase == null) {
            throw new NullPointerException("At least one of the following need to be given: Date, Phrase");
        }
        this.type = type;
        this.isInterpreted = isInterpreted;
        this.date = date;
        this.phrase = phrase;
    }

    public DateValue(@NonNull DateCalendar type, @NonNull IDate date, boolean isInterpreted) {
        if (type != DateCalendar.DGREGORIAN) {
            throw new IllegalStateException("current type is not supported: " + type.toString());
        }
        this.type = type;
        this.isInterpreted = isInterpreted;
        this.date = date;
    }

    public DateValue(@NonNull DateCalendar type, @NonNull IDate date) {
        if (type != DateCalendar.DGREGORIAN) {
            throw new IllegalStateException("current type is not supported: " + type.toString());
        }
        this.type = type;
        this.date = date;
    }

    public DateValue(@NonNull String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String toString(final int level) {
        final StringBuilder sb = new StringBuilder();

        sb.append(level);
        sb.append(" DATE ");

        if (isInterpreted) {
            sb.append("INT ");
        }

        sb.append(date.toString());

        if (phrase != null) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(phrase);
        }

        sb.append(fullStop);
        return sb.toString();
    }
}
