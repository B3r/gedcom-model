package it.marvin_flock.gedcom.dates;

import it.marvin_flock.gedcom.GedcomElement;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class DateExact extends GedcomElement {
    private final LocalDate date;

    public DateExact(@NonNull LocalDate date) {
        this.date = date;
    }

    public String toString(final int level) {
        final StringBuilder sb = new StringBuilder();
        final String gedcomDatePattern = "dd MMM yyyy";
        final DateTimeFormatter gedcomDateFormatter = DateTimeFormatter.ofPattern(gedcomDatePattern);

        appendSimpleStringFor("DATE", gedcomDateFormatter.format(date), level, sb);

        return sb.toString();
    }
}
