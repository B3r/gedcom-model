package it.marvin_flock.gedcom.structures;


import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.sources.SourceCitation;
import lombok.NonNull;

import java.util.List;

public class PlaceStructure extends GedcomElement {

    private final String place;
    private final String form;
    private final List<SourceCitation> citations;
    private final List<NoteStructure> notes;

    public PlaceStructure(Builder builder) {
        this.place = builder.place;
        this.form = builder.form;
        this.citations = builder.citations;
        this.notes = builder.notes;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("PLAC", place, level, sb);
        appendSimpleStringFor("FORM", form, subLevel, sb);

        if (citations != null) {
            citations.forEach(sourceCitation -> sb.append(sourceCitation.toString(level)));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(level)));
        }

        return sb.toString();
    }

    public static class Builder {
        private final String place;
        private String form;
        private List<SourceCitation> citations;
        private List<NoteStructure> notes;

        public Builder(@NonNull String place) {
            this.place = place;
        }

        public Builder withForm(@NonNull String form) {
            this.form = form;
            return this;
        }

        public Builder withSourceCitations(@NonNull List<SourceCitation> citations) {
            this.citations = citations;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public PlaceStructure build() {
            return new PlaceStructure(this);
        }
    }
}
