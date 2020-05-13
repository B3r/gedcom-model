package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.UserReference;
import it.marvin_flock.gedcom.dates.ChangeDate;
import it.marvin_flock.gedcom.structures.AddressStructure;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.util.List;

public class RepositoryRecord extends Record {

    private final String name;
    private final AddressStructure address;
    private final List<NoteStructure> notes;
    private final List<UserReference> userRefs;
    private final String recordId;
    private final ChangeDate changeDate;

    public RepositoryRecord(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.address = builder.address;
        this.notes = builder.notes;
        this.userRefs = builder.userRefs;
        this.recordId = builder.recordId;
        this.changeDate = builder.changeDate;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleIdFor("REPO", id, level, sb);
        appendSimpleStringFor("NAME", name, subLevel, sb);

        if (address != null) {
            sb.append(address.toString(subLevel));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
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

        private final int id;
        private final String name;
        private AddressStructure address;
        private List<NoteStructure> notes;
        private List<UserReference> userRefs;
        private String recordId;
        private ChangeDate changeDate;

        public Builder(int id, @NonNull String name) {
            this.id = id;
            this.name = name;
        }

        public Builder withAddress(@NonNull AddressStructure address) {
            this.address = address;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
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

        public RepositoryRecord build() {
            return new RepositoryRecord(this);
        }
    }
}
