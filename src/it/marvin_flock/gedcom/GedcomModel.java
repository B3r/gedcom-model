package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.records.Record;
import it.marvin_flock.gedcom.records.SubmissionRecord;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * full gedcom model
 * it automatically appends a TRLR
 * this model is based on http://homepages.rootsweb.com/~pmcbride/gedcom/55gcch2.htm
 */
@Getter
public class GedcomModel {

    private final Header header;
    private final SubmissionRecord submissionRecord;
    private final List<Record> records;

    private GedcomModel(Builder builder) {
        this.header = builder.header;
        this.submissionRecord = builder.submissionRecord;
        this.records = builder.records;
    }

    @Override
    public String toString() {
        int startLevel = 0;
        final StringBuilder sb = new StringBuilder();

        // append header
        sb.append(header.toString(startLevel));

        // append submission
        if (submissionRecord != null) {
            sb.append(submissionRecord.toString(startLevel));
        }

        // append all records
        records.forEach(record -> sb.append(record.toString(startLevel)));

        // append trailer
        sb.append(startLevel);
        sb.append(" TRLR");
        return sb.toString();
    }

    public static class Builder {
        private final Header header;
        private SubmissionRecord submissionRecord;
        private final List<Record> records;

        public Builder(Header header, List<Record> records) {
            if (header == null || records == null) {
                throw new IllegalArgumentException("header and records can not be null");
            }
            this.header = header;
            this.records = records;
        }

        public Builder withSubmissionRecord(@NonNull SubmissionRecord submissionRecord) {
            this.submissionRecord = submissionRecord;
            return this;
        }

        public GedcomModel build() {
            return new GedcomModel(this);
        }
    }
}
