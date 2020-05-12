package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.MediaLink;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.util.List;


/**
 * Dont know if this is an error in specification
 */
public class SourceCitationNotable extends SourceCitation {
    private final List<MediaLink> media;
    private final List<NoteStructure> advNotes;

    public SourceCitationNotable(Builder builder) {
        super(builder);
        this.media = builder.media;
        this.advNotes = builder.advNotes;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        sb.append(super.toString(level));

        if (media != null) {
            media.forEach(medium -> sb.append(medium.toString(subLevel)));
        }

        if (advNotes != null) {
            advNotes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        return sb.toString();
    }

    public static class Builder extends SourceCitation.Builder {
        private List<MediaLink> media;
        private List<NoteStructure> advNotes;

        public Builder(@NonNull Integer sourceReferenceId) {
            super(sourceReferenceId);
        }

        public Builder(@NonNull String description) {
            super(description);
        }

        public Builder withMediaLinks(@NonNull List<MediaLink> mmLinks) {
            this.media = mmLinks;
            return this;
        }

        public Builder withAdvancedNotes(@NonNull List<NoteStructure> notes) {
            this.advNotes = notes;
            return this;
        }

        public SourceCitationNotable build() {
            return new SourceCitationNotable(this);
        }
    }
}
