package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.GedcomElement;
import lombok.NonNull;

import java.util.List;

public class NoteStructure extends GedcomElement {

    private final List<Integer> sourRef;
    private String text;
    private Integer noteRef;

    public NoteStructure(@NonNull Integer noteRef, List<Integer> sourRef) {
        this.noteRef = noteRef;
        this.sourRef = sourRef;
    }

    public NoteStructure(String text, List<Integer> sourRef) {
        this.text = text;
        this.sourRef = sourRef;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();
        if (text == null && noteRef == null) {
            appendBlankFor("NOTE", level, sb);
        } else if (noteRef != null) {
            appendReferenceFor("NOTE", noteRef, level, sb);
        } else {
            appendMultiLineFor("NOTE", text, CONT, LINE_SIZE_248, level, sb);
        }
        if (sourRef != null) {
            sourRef.forEach(sourceCitation -> appendReferenceFor("SOUR", sourceCitation, subLevel, sb));
        }
        return sb.toString();
    }
}
