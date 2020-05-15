package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.dates.ChangeDate;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SubmissionRecord extends Record {

    private Integer submitterReferenceId;
    private String familyFilename;
    private String templeCode;
    private Integer generationsOfAncestors;
    private Integer generationsOfDescendants;
    private Boolean ordinanceProcess;
    private String recordId;
    private List<NoteStructure> notes;
    private ChangeDate changeDate;

    public SubmissionRecord(Builder builder) {
        super(builder.id);
        this.submitterReferenceId = builder.submitterReferenceId;
        this.familyFilename = builder.familyFilename;
        this.templeCode = builder.templeCode;
        this.generationsOfAncestors = builder.generationsOfAncestors;
        this.generationsOfDescendants = builder.generationsOfDescendants;
        this.ordinanceProcess = builder.ordinanceProcess;
        this.recordId = builder.recordId;
        this.notes = builder.notes;
        this.changeDate = builder.changeDate;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleIdFor("SUBN", id, level, sb);

        if (submitterReferenceId != null) {
            appendReferenceFor("SUBM", submitterReferenceId, subLevel, sb);
        }
        appendSimpleStringFor("FAMF", familyFilename, subLevel, sb);
        appendSimpleStringFor("TEMP", templeCode, subLevel, sb);

        if (generationsOfAncestors != null) {
            appendSimpleStringFor("ANCE", Integer.toString(generationsOfAncestors), subLevel, sb);
        }
        if (generationsOfDescendants != null) {
            appendSimpleStringFor("DESC", Integer.toString(generationsOfDescendants), subLevel, sb);
        }

        // null check needed to unset ORDI flag
        if (ordinanceProcess != null) {
            String flag = ordinanceProcess ? "yes" : "no";
            appendSimpleStringFor("ORDI", flag, subLevel, sb);
        }

        appendSimpleStringFor("RIN", recordId, subLevel, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        if (changeDate != null) {
            sb.append(changeDate.toString(subLevel));
        }

        return sb.toString();
    }

    public static class Builder {

        private final int id;
        private Integer submitterReferenceId;
        private String familyFilename;
        private String templeCode;
        private Integer generationsOfAncestors;
        private Integer generationsOfDescendants;
        private Boolean ordinanceProcess;
        private String recordId;
        private List<NoteStructure> notes;
        private ChangeDate changeDate;

        public Builder(int id) {
            this.id = id;
        }

        public Builder withSubmitter(@NonNull Integer submitterReferenceId) {
            this.submitterReferenceId = submitterReferenceId;
            return this;
        }

        public Builder withFamilyFilename(@NonNull String familyFilename) {
            this.familyFilename = familyFilename;
            return this;
        }

        public Builder withTempleCode(@NonNull String templeCode) {
            this.templeCode = templeCode;
            return this;
        }

        public Builder withGenerationsOfAncestors(@NonNull Integer generationsOfAncestors) {
            this.generationsOfAncestors = generationsOfAncestors;
            return this;
        }

        public Builder withGenerationsOfDescendants(@NonNull Integer generationsOfDescendants) {
            this.generationsOfDescendants = generationsOfDescendants;
            return this;
        }

        public Builder withIsOrdinanceProcess(@NonNull Boolean isOrdinanceProcess) {
            this.ordinanceProcess = isOrdinanceProcess;
            return this;
        }

        public Builder withRecordId(@NonNull String recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public Builder withChangeDate(@NonNull ChangeDate changeDate) {
            this.changeDate = changeDate;
            return this;
        }

        public SubmissionRecord build() {
            return new SubmissionRecord(this);
        }
    }
}
