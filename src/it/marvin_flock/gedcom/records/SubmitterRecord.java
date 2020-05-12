package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.Addr;
import it.marvin_flock.gedcom.MediaLink;
import it.marvin_flock.gedcom.dates.ChangeDate;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class SubmitterRecord extends Record {

    private final String name;
    private final Addr address;
    private final List<MediaLink> mmLinks;
    private final List<String> languages;
    private final String registerId;
    private final String recordId;
    private final ChangeDate changeDate;

    public SubmitterRecord(Builder builder) {
        super(builder.id);
        this.name = builder.name;
        this.address = builder.address;
        this.mmLinks = builder.mmLinks;
        this.languages = builder.languages;
        this.registerId = builder.registerId;
        this.recordId = builder.recordId;
        this.changeDate = builder.changeDate;
    }

    @Override
    public String toString(final int level) {
        if (name == null) {
            throw new NullPointerException("name may not be empty");
        }
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleIdFor("SUBM", id, level, sb);
        appendSimpleStringFor("NAME", name, subLevel, sb);

        if (address != null) {
            sb.append(address.toString(subLevel));
        }

        if (mmLinks != null) {
            mmLinks.forEach(mmLink -> sb.append(mmLink.toString(subLevel)));
        }

        appendMaxThreeFor(languages, "LANG", level, sb);

        appendSimpleStringFor("RFN", registerId, subLevel, sb);
        appendSimpleStringFor("RIN", recordId, subLevel, sb);

        if (changeDate != null) {
            sb.append(changeDate.toString(subLevel));
        }

        return sb.toString();
    }

    public static class Builder {

        private final String name;
        private final int id;
        private Addr address;
        private List<MediaLink> mmLinks;
        private List<String> languages;
        private String registerId;
        private String recordId;
        private ChangeDate changeDate;

        public Builder(int id, @NonNull String name) {
            this.id = id;
            this.name = name;
        }

        public Builder withAddress(@NonNull Addr address) {
            this.address = address;
            return this;
        }

        public Builder withLanguages(@NonNull List<String> languages) {
            this.languages = languages;
            return this;
        }

        public Builder withRegisterId(@NonNull String registerId) {
            this.registerId = registerId;
            return this;
        }

        public Builder withMultimediaLinks(@NonNull List<MediaLink> mmLinks) {
            this.mmLinks = mmLinks;
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

        public SubmitterRecord build() {
            return new SubmitterRecord(this);
        }
    }

}
