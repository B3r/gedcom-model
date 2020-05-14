package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.MediaLink;
import it.marvin_flock.gedcom.enums.Quay;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SourceCitation extends GedcomElement {
    private final Integer sourceReferenceId;
    private final String page;
    private final SourceCitationEvent event;
    private final SourceCitationData data;
    private final Quay quay;

    private final List<MediaLink> mmLinks;
    private final List<NoteStructure> notes;

    private final String description;
    private final String sourceText;

    protected SourceCitation(Builder builder) {
        this.sourceReferenceId = builder.sourceReferenceId;
        this.page = builder.page;
        this.event = builder.event;
        this.data = builder.data;
        this.quay = builder.quay;
        this.mmLinks = builder.mmLinks;
        this.notes = builder.notes;
        this.description = builder.description;
        this.sourceText = builder.sourceText;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        if (sourceReferenceId != null) {
            appendReferenceFor("SOUR", sourceReferenceId, level, sb);
            appendSimpleStringFor("PAGE", page, subLevel, sb);

            if (event != null) {
                sb.append(event.toString(subLevel));
            }

            if (data != null) {
                sb.append(data.toString(subLevel));
            }
        } else {
            appendMultiLineFor("SOUR", description, CONT, LINE_SIZE_248, level, sb);
            appendMultiLineFor("TEXT", sourceText, CONT, LINE_SIZE_248, subLevel, sb);
            if (notes != null) {
                notes.forEach(note -> sb.append(note.toString(subLevel)));
            }
            return sb.toString();
        }

        if (mmLinks != null) {
            mmLinks.forEach(mmLink -> sb.append(mmLink.toString(subLevel)));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        if (quay != null) {
            appendSimpleStringFor("QUAY", Integer.toString(quay.ordinal()), subLevel, sb);
        }

        return sb.toString();
    }


    public static class Builder {
        private Integer sourceReferenceId;
        private String page;
        private SourceCitationEvent event;
        private SourceCitationData data;
        private Quay quay;
        private List<MediaLink> mmLinks;
        private List<NoteStructure> notes;
        private String description;
        private String sourceText;

        public Builder(@NonNull Integer sourceReferenceId) {
            this.sourceReferenceId = sourceReferenceId;
        }

        public Builder(@NonNull String description) {
            this.description = description;
        }

        public Builder withPage(@NonNull String page) {
            this.page = page;
            return this;
        }

        public Builder withEvent(@NonNull SourceCitationEvent event) {
            this.event = event;
            return this;
        }

        public Builder withData(@NonNull SourceCitationData data) {
            this.data = data;
            return this;
        }

        public Builder withQuay(@NonNull Quay quay) {
            this.quay = quay;
            return this;
        }

        public Builder withMultimediaLinks(@NonNull List<MediaLink> mmLinks) {
            this.mmLinks = mmLinks;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public Builder withDescription(@NonNull String description) {
            this.description = description;
            return this;
        }

        public Builder withSourceText(@NonNull String sourceText) {
            this.sourceText = sourceText;
            return this;
        }

        public SourceCitation build() {
            return new SourceCitation(this);
        }
    }
}
