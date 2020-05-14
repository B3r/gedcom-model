package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.dates.DatePeriod;
import it.marvin_flock.gedcom.enums.EventAttribute;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class RecordedEvent extends GedcomElement {
    private final List<EventAttribute> types;
    private final DatePeriod date;
    private final String place;

    public RecordedEvent(@NonNull List<EventAttribute> types, DatePeriod date, String place) {
        this.types = types;
        this.date = date;
        this.place = place;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        String typeString = types.stream().map(Enum::toString).collect(Collectors.joining(", "));
        appendSimpleStringFor("EVEN", typeString, level, sb);

        if (date != null) {
            appendSimpleStringFor("DATE", date.toString(), subLevel, sb);
        }

        if (place != null) {
            appendSimpleStringFor("PLAC", place, subLevel, sb);
        }

        return sb.toString();
    }
}
