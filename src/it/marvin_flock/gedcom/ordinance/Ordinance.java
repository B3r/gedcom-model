package it.marvin_flock.gedcom.ordinance;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.dates.DateValue;
import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Ordinance extends GedcomElement {

    protected DateValue date;
    private String templeCode;
    private String place;
    private List<SourceCitation> sourceCitations;
    private List<NoteStructure> notes;

    public Ordinance() {
    }

    public Ordinance(DateValue date, String templeCode, String place, List<SourceCitation> sourceCitations, List<NoteStructure> notes) {
        this.date = date;
        this.templeCode = templeCode;
        this.place = place;
        this.sourceCitations = sourceCitations;
        this.notes = notes;
    }

    @Override
    public String toString(int level) {
        final StringBuilder sb = new StringBuilder();
        if (date != null) {
            sb.append(date.toString(level));
        }
        appendSimpleStringFor("TEMP", templeCode, level, sb);
        appendSimpleStringFor("PLAC", place, level, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(level)));
        }

        if (sourceCitations != null) {
            sourceCitations.forEach(sourceCitation -> sb.append(sourceCitation.toString(level)));
        }

        return sb.toString();
    }
}
