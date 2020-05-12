package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.UserReference;
import it.marvin_flock.gedcom.dates.ChangeDate;
import it.marvin_flock.gedcom.sources.SourceCitation;
import lombok.NonNull;

import java.util.List;

public class NoteRecord extends Record {

    private final String text;
    private final List<SourceCitation> sourceCitations;
    private final List<UserReference> userRefs;
    private final String recordId;
    private final ChangeDate changeDate;

    public NoteRecord(Builder builder) {
        super(builder.id);
        this.text = builder.text;
        this.sourceCitations = builder.sourceCitations;
        this.userRefs = builder.userRefs;
        this.recordId = builder.recordId;
        this.changeDate = builder.changeDate;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        sb.append(level);
        sb.append(" ");
        sb.append(asXRef(id));
        sb.append(" NOTE ");
        final List<String> multilines = splitString(text, LINE_SIZE_248);
        if (!multilines.isEmpty()) {
            sb.append(multilines.get(0));
        }
        sb.append(fullStop);

        multilines.remove(0);
        multilines.forEach(line -> appendSimpleStringFor(CONT, line, subLevel, sb));

        if (sourceCitations != null) {
            sourceCitations.forEach(sourceCitation -> sb.append(sourceCitation.toString(subLevel)));
        }

        if (userRefs != null) {
            userRefs.forEach(userReference -> sb.append(userReference.toString(subLevel)));
        }

        appendSimpleStringFor("RIN", recordId, subLevel, sb);

        if (changeDate != null) {
            sb.append(changeDate.toString(subLevel));
        }

        return sb.toString();
    }

    public static class Builder {

        private final String text;
        private final int id;
        private List<SourceCitation> sourceCitations;
        private List<UserReference> userRefs;
        private String recordId;
        private ChangeDate changeDate;

        public Builder(int id, @NonNull String text) {
            this.id = id;
            this.text = text;
        }

        public Builder withSourceCitations(@NonNull List<SourceCitation> sourceCitations) {
            this.sourceCitations = sourceCitations;
            return this;
        }

        public Builder withUserReferences(@NonNull List<UserReference> userRefs) {
            this.userRefs = userRefs;
            return this;
        }

        public Builder withRecordId(@NonNull String recordId) {
            this.recordId = recordId;
            return this;
        }

        public Builder withChangeDate(@NonNull ChangeDate changeDate) {
            this.changeDate = changeDate;
            return this;
        }

        public NoteRecord build() {
            return new NoteRecord(this);
        }
    }

}
