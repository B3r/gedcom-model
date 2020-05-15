package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.dates.DateValue;
import it.marvin_flock.gedcom.enums.Restriction;
import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.AddressStructure;
import it.marvin_flock.gedcom.structures.NoteStructure;
import it.marvin_flock.gedcom.structures.PlaceStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDetail extends GedcomElement {

    private String type;
    private DateValue date;
    private PlaceStructure place;
    private AddressStructure address;
    private String agency;
    private String religiousAffiliation;
    private String cause;
    private Restriction restriction;
    private List<NoteStructure> notes;
    private List<SourceCitation> sourceCitations;
    private List<MediaLink> mmLinks;

    public EventDetail(Builder builder) {
        this.type = builder.type;
        this.date = builder.date;
        this.place = builder.place;
        this.address = builder.address;
        this.agency = builder.agency;
        this.religiousAffiliation = builder.religiousAffiliation;
        this.cause = builder.cause;
        this.restriction = builder.restriction;
        this.sourceCitations = builder.sourceCitations;
        this.mmLinks = builder.mmLinks;
        this.notes = builder.notes;
    }

    @Override
    public String toString(final int level) {
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("TYPE", type, level, sb);

        if (date != null) {
            sb.append(date.toString(level));
        }

        if (place != null) {
            sb.append(place.toString(level));
        }

        if (address != null) {
            sb.append(address.toString(level));
        }

        appendSimpleStringFor("AGNC", agency, level, sb);
        appendSimpleStringFor("RELI", religiousAffiliation, level, sb);
        appendSimpleStringFor("CAUS", cause, level, sb);

        if (restriction != null) {
            appendSimpleStringFor("RESN", restriction.toString().toLowerCase(), level, sb);
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(level)));
        }

        if (sourceCitations != null) {
            sourceCitations.forEach(sourceCitation-> sb.append(sourceCitation.toString(level)));
        }

        if (mmLinks != null) {
            mmLinks.forEach(mmLink -> sb.append(mmLink.toString(level)));
        }

        return sb.toString();
    }

    public static class Builder {

        private String religiousAffiliation;
        private Restriction restriction;
        private String type;
        private DateValue date;
        private PlaceStructure place;
        private AddressStructure address;
        private String agency;
        private String cause;
        private List<SourceCitation> sourceCitations;
        private List<MediaLink> mmLinks;
        private List<NoteStructure> notes;

        public Builder() {
            // no mandatory fields
        }

        public Builder withType(@NonNull String type) {
            this.type = type;
            return this;
        }

        public Builder withRestriction(@NonNull Restriction restriction) {
            this.restriction = restriction;
            return this;
        }

        public Builder withReligiousAffiliation(@NonNull String religiousAffiliation) {
            this.religiousAffiliation = religiousAffiliation;
            return this;
        }

        public Builder withDate(@NonNull DateValue date) {
            this.date = date;
            return this;
        }

        public Builder withPlace(@NonNull PlaceStructure place) {
            this.place = place;
            return this;
        }

        public Builder withAddress(@NonNull AddressStructure address) {
            this.address = address;
            return this;
        }

        public Builder withAgency(@NonNull String agency) {
            this.agency = agency;
            return this;
        }

        public Builder withCause(@NonNull String cause) {
            this.cause = cause;
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

        public EventDetail build() {
            return new EventDetail(this);
        }
    }
}
