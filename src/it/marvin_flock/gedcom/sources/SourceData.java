package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.RecordedEvent;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SourceData extends GedcomElement {

    private List<RecordedEvent> events;
    private String agency;
    private List<NoteStructure> notes;

    public SourceData() {
    }

    public SourceData(List<RecordedEvent> events) {
        this.events = events;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendBlankFor("DATA", level, sb);

        events.forEach(event -> sb.append(event.toString(subLevel)));

        appendSimpleStringFor("AGNC", agency, subLevel, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }
        return sb.toString();
    }
}
