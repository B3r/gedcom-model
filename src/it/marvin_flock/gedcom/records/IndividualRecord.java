package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.ChildFamilyLink;
import it.marvin_flock.gedcom.MediaLink;
import it.marvin_flock.gedcom.SpouseFamilyLink;
import it.marvin_flock.gedcom.UserReference;
import it.marvin_flock.gedcom.dates.ChangeDate;
import it.marvin_flock.gedcom.enums.Restriction;
import it.marvin_flock.gedcom.enums.Sex;
import it.marvin_flock.gedcom.ordinance.Ordinance;
import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.*;
import lombok.NonNull;

import java.util.List;

public class IndividualRecord extends Record {

    private final Restriction restrictionNotice;
    private final List<NameStructure> names;
    private final Sex sex;
    private final List<IndividualEventStructure> events;
    private final List<IndividualAttributeStructure> attributes;
    private final List<Ordinance> ordinances;
    private final List<ChildFamilyLink> cLinks;
    private final List<SpouseFamilyLink> sLinks;
    private final List<Integer> submitters;
    private final List<AssociationStructure> associations;
    private final List<Integer> aliases;
    private final List<Integer> ancis;
    private final List<Integer> desis;
    private final List<SourceCitation> sourceCitations;
    private final List<MediaLink> mmLinks;
    private final List<NoteStructure> notes;
    private final String recordFileNr;
    private final String ancestralFileNr;
    private final List<UserReference> userRefs;
    private final String recordId;
    private final ChangeDate changeDate;

    public IndividualRecord(Builder builder) {
        super(builder.id);
        this.restrictionNotice = builder.restrictionNotice;
        this.names = builder.names;
        this.sex = builder.sex;
        this.events = builder.events;
        this.attributes = builder.attributes;
        this.ordinances = builder.ordinances;
        this.cLinks = builder.cLinks;
        this.sLinks = builder.sLinks;
        this.submitters = builder.submitters;
        this.associations = builder.associations;
        this.aliases = builder.aliases;
        this.ancis = builder.ancis;
        this.desis = builder.desis;
        this.sourceCitations = builder.sourceCitations;
        this.mmLinks = builder.mmLinks;
        this.notes = builder.notes;
        this.recordFileNr = builder.recordFileNr;
        this.ancestralFileNr = builder.ancestralFileNr;
        this.userRefs = builder.userRefs;
        this.recordId = builder.recordId;
        this.changeDate = builder.changeDate;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleIdFor("INDI", id, level, sb);

        if (restrictionNotice != null) {
            appendSimpleStringFor("RESN", restrictionNotice.toString().toLowerCase(), subLevel, sb);
        }

        if (names != null) {
            names.forEach(name -> sb.append(name.toString(subLevel)));
        }

        if (sex != null) {
            appendSimpleStringFor("SEX", sex.toString(), subLevel, sb);
        }

        if (events != null) {
            events.forEach(event -> sb.append(event.toString(subLevel)));
        }

        if (attributes != null) {
            attributes.forEach(attribute -> sb.append(attribute.toString(subLevel)));
        }

        if (ordinances != null) {
            ordinances.forEach(ordinance -> sb.append(ordinance.toString(subLevel)));
        }

        if (cLinks != null) {
            cLinks.forEach(cLink -> sb.append(cLink.toString(subLevel)));
        }

        if (sLinks != null) {
            sLinks.forEach(sLink -> sb.append(sLink.toString(subLevel)));
        }

        if (submitters != null) {
            submitters.forEach(submitter -> appendReferenceFor("SUBM", submitter, subLevel, sb));
        }

        if (associations != null) {
            associations.forEach(association -> sb.append(association.toString(subLevel)));
        }

        if (aliases != null) {
            aliases.forEach(alias -> appendReferenceFor("ALIA", alias, subLevel, sb));
        }

        if (ancis != null) {
            ancis.forEach(anci -> appendReferenceFor("ANCI", anci, subLevel, sb));
        }

        if (desis != null) {
            desis.forEach(desi -> appendReferenceFor("DESI", desi, subLevel, sb));
        }

        appendSimpleStringFor("RFN", recordFileNr, subLevel, sb);
        appendSimpleStringFor("AFN", ancestralFileNr, subLevel, sb);

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
        private List<NameStructure> names;
        private Sex sex;
        private List<IndividualEventStructure> events;
        private List<IndividualAttributeStructure> attributes;
        private List<Ordinance> ordinances;
        private List<ChildFamilyLink> cLinks;
        private List<SpouseFamilyLink> sLinks;
        private List<Integer> submitters;
        private List<AssociationStructure> associations;
        private List<Integer> aliases;
        private List<Integer> ancis;
        private List<Integer> desis;
        private List<SourceCitation> sourceCitations;
        private List<MediaLink> mmLinks;
        private List<NoteStructure> notes;
        private String recordFileNr;
        private String ancestralFileNr;
        private List<UserReference> userRefs;
        private String recordId;
        private ChangeDate changeDate;

        public Builder(int id) {
            this.id = id;
        }

        public Builder withRestriction(@NonNull Restriction restriction) {
            this.restrictionNotice = restriction;
            return this;
        }

        public Builder withNames(@NonNull List<NameStructure> names) {
            this.names = names;
            return this;
        }

        public Builder withSex(@NonNull Sex sex) {
            this.sex = sex;
            return this;
        }

        public Builder withEvents(@NonNull List<IndividualEventStructure> events) {
            this.events = events;
            return this;
        }

        public Builder withAttributes(@NonNull List<IndividualAttributeStructure> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withOrdinance(@NonNull List<Ordinance> ordinances) {
            this.ordinances = ordinances;
            return this;
        }

        public Builder withChildFamilyLinks(@NonNull List<ChildFamilyLink> cLinks) {
            this.cLinks = cLinks;
            return this;
        }

        public Builder withSpouseFamilyLinks(@NonNull List<SpouseFamilyLink> sLinks) {
            this.sLinks = sLinks;
            return this;
        }

        public Builder withSubmitters(@NonNull List<Integer> submitters) {
            this.submitters = submitters;
            return this;
        }

        public Builder withAssociations(@NonNull List<AssociationStructure> associations) {
            this.associations = associations;
            return this;
        }

        public Builder withAliases(@NonNull List<Integer> aliases) {
            this.aliases = aliases;
            return this;
        }

        public Builder withAncis(@NonNull List<Integer> ancis) {
            this.ancis = ancis;
            return this;
        }

        public Builder withDesis(@NonNull List<Integer> desis) {
            this.desis = desis;
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

        public Builder withRecordFileNr(@NonNull String recordFileNr) {
            this.recordFileNr = recordFileNr;
            return this;
        }

        public Builder withAncestralFileNr(@NonNull String ancestralFileNr) {
            this.ancestralFileNr = ancestralFileNr;
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

        public IndividualRecord build() {
            return new IndividualRecord(this);
        }
    }
}
