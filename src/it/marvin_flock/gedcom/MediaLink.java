package it.marvin_flock.gedcom;


import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.util.List;

/**
 * TODO embedded form is pointing to MULTIMEDIA_RECORD
 * NEED TO FIGURE OUT HOW NEWER FILE FORMATS ARE COMPATIBLE WITH GEDCOM
 * Allowed formats based on 5.5 Specifications:
 * [ bmp | gif | jpeg | ole | pcx | tiff | wav ]
 */
public class MediaLink extends GedcomElement {
    private final Integer mmRecordId;
    private final String format;
    private final String title;
    private final String fileReference;
    private final List<NoteStructure> notes;

    private MediaLink(Builder builder) {
        this.mmRecordId = builder.mmRecordId;
        this.format = builder.format;
        this.title = builder.title;
        this.fileReference = builder.fileReference;
        this.notes = builder.notes;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        if (mmRecordId != null) {
            appendReferenceFor("OBJE", mmRecordId, level, sb);
            return sb.toString();
        }

        appendBlankFor("OBJE", level, sb);
        appendSimpleStringFor("FORM", format, subLevel, sb);
        appendSimpleStringFor("TITL", title, subLevel, sb);
        appendSimpleStringFor("FILE", fileReference, subLevel, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }
        return sb.toString();
    }


    public static class Builder {

        private Integer mmRecordId;
        private String format;
        private String title;
        private String fileReference;
        private List<NoteStructure> notes;

        public Builder(@NonNull Integer multiMediaRecordReferenceId) {
            this.mmRecordId = multiMediaRecordReferenceId;
        }

        public Builder(@NonNull String format, @NonNull String fileReference) {
            this.format = format;
            this.fileReference = fileReference;
        }

        public Builder withTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public MediaLink build() {
            return new MediaLink(this);
        }
    }
}
