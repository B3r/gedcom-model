package it.marvin_flock.gedcom.dates;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class DateTime extends DateExact {

    private LocalTime time;

    public DateTime(@NonNull LocalDate date) {
        super(date);
    }

    public DateTime(@NonNull LocalDate date, @NonNull LocalTime time) {
        super(date);
        this.time = time;
    }

    public String toString(int level) {
        final int subLevel = level + 1;
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString(level));
        if (time != null) {
            final String timeColonPattern = "HH:mm:ss:SSS";
            final DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);

            sb.append(subLevel);
            sb.append(" TIME ");
            sb.append(timeColonFormatter.format(time));
            sb.append(fullStop);
        }
        return sb.toString();
    }
}
