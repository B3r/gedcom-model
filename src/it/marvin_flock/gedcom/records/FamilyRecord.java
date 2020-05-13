package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.MediaLink;
import it.marvin_flock.gedcom.UserReference;
import it.marvin_flock.gedcom.dates.ChangeDate;
import it.marvin_flock.gedcom.enums.Restriction;
import it.marvin_flock.gedcom.ordinance.SpouseSealing;
import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.FamilyEventStructure;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.util.List;

public class FamilyRecord extends Record {

    private final Restriction restrictionNotice;
    private final List<FamilyEventStructure> events;
    private final Integer husband;
    private final Integer wife;
    private final List<Integer> children;
    private final Integer childrenCount;
    private final List<Integer> submitters;
    private final List<SpouseSealing> spouseSealings;
    private final List<UserReference> userRefs;
    private final List<SourceCitation> sourceCitations;
    private final List<MediaLink> mmLinks;
    private final List<NoteStructure> notes;
    private final String recordId;
    private final ChangeDate changeDate;

    public FamilyRecord(Builder builder) {
        super(builder.id);
        this.restrictionNotice = builder.restrictionNotice;
        this.events = builder.events;
        this.husband = builder.husband;
        this.wife = builder.wife;
        this.children = builder.children;
        this.childrenCount = builder.childrenCount;
        this.submitters = builder.submitters;
        this.spouseSealings = builder.spouseSealings;
        this.sourceCitations = builder.sourceCitations;
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

        appendSimpleIdFor("FAM", id, level, sb);

        if (restrictionNotice != null) {
            appendSimpleStringFor("RESN", restrictionNotice.toString().toLowerCase(), subLevel, sb);
        }

        if (events != null) {
            events.forEach(event -> sb.append(event.toString(subLevel)));
        }

        appendReferenceFor("HUSB", husband, subLevel, sb);
        appendReferenceFor("WIFE", wife, subLevel, sb);
        if (children != null) {
            children.forEach(child -> appendReferenceFor("CHIL", child, subLevel, sb));
        }

        if (childrenCount != null) {
            appendSimpleStringFor("NCHI", Integer.toString(childrenCount), subLevel, sb);
        }

        if (submitters != null) {
            submitters.forEach(submitter -> appendReferenceFor("SUBM", submitter, subLevel, sb));
        }

        if (spouseSealings != null) {
            spouseSealings.forEach(spouseSealing -> sb.append(spouseSealing.toString(subLevel)));
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

        if (sourceCitations != null) {
            sourceCitations.forEach(sourceCitation -> sb.append(sourceCitation.toString(subLevel)));
        }

        if (mmLinks != null) {
            mmLinks.forEach(mmLink -> sb.append(mmLink.toString(subLevel)));
        }

        return sb.toString();
    }

    public static class Builder {

        private final int id;
        private Restriction restrictionNotice;
        private List<FamilyEventStructure> events;
        private Integer husband;
        private Integer wife;
        private List<Integer> children;
        private Integer childrenCount;
        private List<Integer> submitters;
        private List<SpouseSealing> spouseSealings;
        private List<SourceCitation> sourceCitations;
        private List<MediaLink> mmLinks;
        private List<NoteStructure> notes;
        private List<UserReference> userRefs;
        private String recordId;
        private ChangeDate changeDate;

        public Builder(int id) {
            this.id = id;
        }

        public Builder withRestrictionNotice(@NonNull Restriction restrictionNotice) {
            this.restrictionNotice = restrictionNotice;
            return this;
        }

        public Builder withEvents(@NonNull List<FamilyEventStructure> events) {
            this.events = events;
            return this;
        }

        public Builder withHusbandId(@NonNull Integer husbandId) {
            this.husband = husbandId;
            return this;
        }

        public Builder withWifeId(@NonNull Integer wifeId) {
            this.wife = wifeId;
            return this;
        }

        public Builder withChildren(@NonNull List<Integer> children) {
            this.children = children;
            return this;
        }

        public Builder withChildrenCount(@NonNull Integer childrenCount) {
            this.childrenCount = childrenCount;
            return this;
        }

        public Builder withSubmitters(@NonNull List<Integer> submitters) {
            this.submitters = submitters;
            return this;
        }

        public Builder withSpouseSealings(@NonNull List<SpouseSealing> spouseSealings) {
            this.spouseSealings = spouseSealings;
            return this;
        }

        public Builder withSourceCitations(@NonNull List<SourceCitation> sourceCitations) {
            this.sourceCitations = sourceCitations;
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

        public FamilyRecord build() {
            return new FamilyRecord(this);
        }
    }
}
