package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.sources.SourceCitation;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

/**
 * Specification says that individualId is {0:M}, is it a copy/paste error?
 * How can it be 0 when there is a mandatory Relation {1:1}?
 */
@Getter
@Setter
public class AssociationStructure extends GedcomElement {
    private final Integer individualId;
    private final String relation;
    private final List<NoteStructure> notes;
    private final List<SourceCitation> sourceCitations;

    public AssociationStructure(Builder builder) {
        this.individualId = builder.individualId;
        this.relation = builder.relation;
        this.notes = builder.notes;
        this.sourceCitations = builder.sourceCitations;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendReferenceFor("ASSO", individualId, level, sb);
        appendSimpleStringFor("RELA", relation, subLevel, sb);

        if (sourceCitations != null) {
            sourceCitations.forEach(sourceCitation -> sb.append(sourceCitation.toString(subLevel)));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        return sb.toString();
    }

    public static class Builder {
        private final Integer individualId;
        private final String relation;
        private List<NoteStructure> notes;
        private List<SourceCitation> sourceCitations;

        public Builder(@NonNull Integer individualId, @NonNull String relation) {
            this.individualId = individualId;
            this.relation = relation;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public Builder withSourceCitations(@NonNull List<SourceCitation> sourceCitations) {
            this.sourceCitations = sourceCitations;
            return this;
        }

        public AssociationStructure build() {
            return new AssociationStructure(this);
        }
    }
}
