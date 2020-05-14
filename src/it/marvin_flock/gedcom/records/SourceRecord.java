package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.MediaLink;
import it.marvin_flock.gedcom.UserReference;
import it.marvin_flock.gedcom.dates.ChangeDate;
import it.marvin_flock.gedcom.sources.SourceData;
import it.marvin_flock.gedcom.sources.SourceRepositoryCitation;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SourceRecord extends Record {

    private final SourceData data;
    private final String auth;
    private final String title;
    private final String abbr;
    private final String publication;
    private final String sourceText;
    private final SourceRepositoryCitation sourceRepositoryCitation;
    private final List<MediaLink> mmLinks;
    private final List<NoteStructure> notes;
    private final List<UserReference> userRefs;
    private final String recordId;
    private final ChangeDate changeDate;

    public SourceRecord(Builder builder) {
        super(builder.id);
        this.data = builder.data;
        this.auth = builder.auth;
        this.title = builder.title;
        this.abbr = builder.abbr;
        this.publication = builder.publication;
        this.sourceText = builder.sourceText;
        this.sourceRepositoryCitation = builder.sourceRepositoryCitation;
        this.mmLinks = builder.mmLinks;
        this.notes = builder.notes;
        this.userRefs = builder.userRefs;
        this.recordId = builder.recordId;
        this.changeDate = builder.changeDate;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleIdFor("SOUR", id, level, sb);

        if (data != null) {
            sb.append(data.toString(subLevel));
        }

        appendMultiLineFor("AUTH", auth, CONT, LINE_SIZE_248, subLevel, sb);
        appendMultiLineFor("TITL", title, CONT, LINE_SIZE_248, subLevel, sb);
        appendSimpleStringFor("ABBR", abbr, subLevel, sb);
        appendMultiLineFor("PUBL", publication, CONT, LINE_SIZE_248, subLevel, sb);
        appendMultiLineFor("TEXT", sourceText, CONT, LINE_SIZE_248, subLevel, sb);

        if (sourceRepositoryCitation != null) {
            sb.append(sourceRepositoryCitation.toString(subLevel));
        }

        if (userRefs != null) {
            userRefs.forEach(userReference -> sb.append(userReference.toString(subLevel)));
        }

        appendSimpleStringFor("RIN", recordId, subLevel, sb);

        if (changeDate != null) {
            sb.append(changeDate.toString(subLevel));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        if (mmLinks != null) {
            mmLinks.forEach(mmLink -> sb.append(mmLink.toString(subLevel)));
        }

        return sb.toString();
    }

    public static class Builder {

        private final int id;
        private SourceData data;
        private String auth;
        private String title;
        private String abbr;
        private String publication;
        private String sourceText;
        private SourceRepositoryCitation sourceRepositoryCitation;
        private List<MediaLink> mmLinks;
        private List<NoteStructure> notes;
        private List<UserReference> userRefs;
        private String recordId;
        private ChangeDate changeDate;

        public Builder(int id) {
            this.id = id;
        }

        public Builder withSourceData(@NonNull SourceData data) {
            this.data = data;
            return this;
        }

        public Builder withAuth(@NonNull String auth) {
            this.auth = auth;
            return this;
        }

        public Builder withTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        public Builder withAbbreviation(@NonNull String abbreviation) {
            this.abbr = abbreviation;
            return this;
        }

        public Builder withPublication(@NonNull String publication) {
            this.publication = publication;
            return this;
        }

        public Builder withSourceText(@NonNull String sourceText) {
            this.sourceText = sourceText;
            return this;
        }

        public Builder withSourceRepositoryCitation(@NonNull SourceRepositoryCitation sourceRepoCitation) {
            this.sourceRepositoryCitation = sourceRepoCitation;
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

        public SourceRecord build() {
            return new SourceRecord(this);
        }
    }
}
