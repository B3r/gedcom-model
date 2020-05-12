package it.marvin_flock.gedcom.records;

import lombok.NonNull;

public class SubmissionRecord extends Record {

    private final Integer submitterReferenceId;
    private final String familyFilename;
    private final String templeCode;
    private final Integer generationsOfAncestors;
    private final Integer generationsOfDescendants;
    private final Boolean ordinanceProcess;
    private final String recordId;

    public SubmissionRecord(Builder builder) {
        super(builder.id);
        this.submitterReferenceId = builder.submitterReferenceId;
        this.familyFilename = builder.familyFilename;
        this.templeCode = builder.templeCode;
        this.generationsOfAncestors = builder.generationsOfAncestors;
        this.generationsOfDescendants = builder.generationsOfDescendants;
        this.ordinanceProcess = builder.ordinanceProcess;
        this.recordId = builder.recordId;
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

        // did not use primitive to set field not at all
        if (ordinanceProcess != null) {
            String flag = ordinanceProcess ? "yes" : "no";
            appendSimpleStringFor("ORDI", flag, subLevel, sb);
        }

        appendSimpleStringFor("RIN", recordId, subLevel, sb);

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

        public SubmissionRecord build() {
            return new SubmissionRecord(this);
        }
    }
}
