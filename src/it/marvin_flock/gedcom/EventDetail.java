package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.dates.DateValue;
import it.marvin_flock.gedcom.dates.GregorianDate;
import it.marvin_flock.gedcom.sources.SourceCitationNotable;
import it.marvin_flock.gedcom.structures.NoteStructure;
import it.marvin_flock.gedcom.structures.PlaceStructure;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class EventDetail extends GedcomElement {

    private final String type;
    private final DateValue date;
    private final PlaceStructure place;
    private final Addr address;
    private final EventAge age;
    private final String agency;
    private final String cause;
    private final List<SourceCitationNotable> sourceCitationNotables;
    private final List<MediaLink> mmLinks;
    private final List<NoteStructure> notes;

    public EventDetail(Builder builder) {
        this.type = builder.type;
        this.date = builder.date;
        this.place = builder.place;
        this.address = builder.address;
        this.age = builder.age;
        this.agency = builder.agency;
        this.cause = builder.cause;
        this.sourceCitationNotables = builder.sourceCitationNotables;
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

        // this one is tricky, if date is an inbetween date, whats the age then?
        if (age != null && date != null && date.getDate() instanceof GregorianDate) {
            sb.append(age.toString(((GregorianDate) date.getDate()).asDate(), level));
        }

        appendSimpleStringFor("AGNC", agency, level, sb);
        appendSimpleStringFor("CAUS", cause, level, sb);

        if (sourceCitationNotables != null) {
            sourceCitationNotables.forEach(sourceCitationNotable -> sb.append(sourceCitationNotable.toString(level)));
        }

        if (mmLinks != null) {
            mmLinks.forEach(mmLink -> sb.append(mmLink.toString(level)));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(level)));
        }

        return sb.toString();
    }

    public static class Builder {

        private String type;
        private DateValue date;
        private PlaceStructure place;
        private Addr address;
        private EventAge age;
        private String agency;
        private String cause;
        private List<SourceCitationNotable> sourceCitationNotables;
        private List<MediaLink> mmLinks;
        private List<NoteStructure> notes;

        public Builder() {
            // no mandatory fields
        }

        public Builder withType(@NonNull String type) {
            this.type = type;
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

        public Builder withAddress(@NonNull Addr address) {
            this.address = address;
            return this;
        }

        public Builder withAge(@NonNull EventAge age) {
            this.age = age;
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

        public Builder withSourceCitations(@NonNull List<SourceCitationNotable> sourceCitations) {
            this.sourceCitationNotables = sourceCitations;
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
