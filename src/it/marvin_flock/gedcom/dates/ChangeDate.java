package it.marvin_flock.gedcom.dates;

import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ChangeDate extends DateTime {
    private List<NoteStructure> notes;

    public ChangeDate(@NonNull LocalDate date) {
        super(date);
    }

    public ChangeDate(@NonNull LocalDate date, LocalTime time) {
        super(date, time);
    }

    public ChangeDate(@NonNull LocalDate date, LocalTime time, List<NoteStructure> notes) {
        super(date, time);
        this.notes = notes;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendBlankFor("CHAN", level, sb);

        sb.append(super.toString(subLevel));

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }
        return sb.toString();
    }
}
