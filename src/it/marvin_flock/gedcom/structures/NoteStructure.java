package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.GedcomElement;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class NoteStructure extends GedcomElement {

    private String text;
    private Integer noteRef;

    public NoteStructure(@NonNull Integer noteRef) {
        this.noteRef = noteRef;
    }

    public NoteStructure(String text) {
        this.text = text;
    }

    @Override
    public String toString(final int level) {
        final StringBuilder sb = new StringBuilder();
        if (text == null && noteRef == null) {
            appendBlankFor("NOTE", level, sb);
        } else if (noteRef != null) {
            appendReferenceFor("NOTE", noteRef, level, sb);
        } else {
            appendMultiLineFor("NOTE", text, CONT, LINE_SIZE_248, level, sb);
        }
        return sb.toString();
    }
}
