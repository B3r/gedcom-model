package it.marvin_flock.gedcom.structures;


import it.marvin_flock.gedcom.Coordinate;
import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.NameVariation;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PlaceStructure extends GedcomElement {

    private final String place;
    private final String form;

    private final List<NameVariation> phonetics;
    private final List<NameVariation> romanized;

    private final Coordinate map;
    private final List<NoteStructure> notes;

    public PlaceStructure(Builder builder) {
        this.place = builder.place;
        this.form = builder.form;
        this.notes = builder.notes;
        this.phonetics = builder.phonetics;
        this.romanized = builder.romanized;
        this.map = builder.map;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("PLAC", place, level, sb);
        appendSimpleStringFor("FORM", form, subLevel, sb);

        if (phonetics != null) {
            phonetics.forEach(phonetic -> sb.append(phonetic.toString(subLevel)));
        }

        if (romanized != null) {
            romanized.forEach(roman -> sb.append(roman.toString(subLevel)));
        }

        if (map != null) {
            sb.append(map.toString(subLevel));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(level)));
        }

        return sb.toString();
    }

    public static class Builder {
        private final String place;
        private Coordinate map;
        private List<NameVariation> phonetics;
        private List<NameVariation> romanized;
        private String form;
        private List<NoteStructure> notes;

        public Builder(@NonNull String place) {
            this.place = place;
        }

        public Builder withForm(@NonNull String form) {
            this.form = form;
            return this;
        }

        public Builder withMap(@NonNull Coordinate map) {
            this.map = map;
            return this;
        }

        public Builder withPhonetics(@NonNull List<NameVariation> phonetics) {
            this.phonetics = phonetics;
            return this;
        }

        public Builder withRomanized(@NonNull List<NameVariation> romanized) {
            this.romanized = romanized;
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
