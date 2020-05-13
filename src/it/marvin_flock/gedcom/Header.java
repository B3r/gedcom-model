package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.dates.DateTime;
import it.marvin_flock.gedcom.sources.Sour;
import lombok.Getter;
import lombok.NonNull;
@Getter
public class Header extends GedcomElement {

    private final Sour source;
    private final String dest;
    private final DateTime transDate;
    private final Integer submitterId;
    private final Integer submissionId;
    private final String filename;
    private final String copyright;
    private final Gedc gedcom;
    private final String charset;
    private final String language;
    private final String place;
    private final String note;

    private Header(Builder builder) {
        this.source = builder.source;
        this.dest = builder.dest;
        this.transDate = builder.transDate;
        this.submitterId = builder.submitterId;
        this.submissionId = builder.submissionId;
        this.filename = builder.filename;
        this.copyright = builder.copyright;
        this.gedcom = builder.gedcom;
        this.charset = builder.charset;
        this.language = builder.language;
        this.place = builder.place;
        this.note = builder.note;
    }

    public String toString(int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendBlankFor("HEAD", level, sb);
        sb.append(source.toString(subLevel));
        appendSimpleStringFor("DEST", dest, subLevel, sb);

        if (transDate != null) {
            sb.append(transDate.toString(subLevel));
        }

        appendReferenceFor("SUBM", submitterId, subLevel, sb);

        if (submissionId != null) {
            appendReferenceFor("SUBN", submissionId, subLevel, sb);
        }

        appendSimpleStringFor("FILE", filename, subLevel, sb);
        appendSimpleStringFor("COPR", copyright, subLevel, sb);

        sb.append(gedcom.toString(subLevel));

        appendSimpleStringFor("CHAR", charset, subLevel, sb);
        appendSimpleStringFor("LANG", language, subLevel, sb);

        if (place != null) {
            appendBlankFor("PLAC", subLevel, sb);
            appendSimpleStringFor("FORM", place, subLevel + 1, sb);
        }

        if (note != null) {
            appendMultiLineFor("NOTE", note, CONT, LINE_SIZE_255, subLevel, sb);
        }

        return sb.toString();
    }

    public static class Builder {
        private final Sour source;
        private String dest;
        private DateTime transDate;
        private final Integer submitterId;
        private Integer submissionId;
        private String filename;
        private String copyright;
        private final Gedc gedcom;
        private final String charset;
        private String language;
        private String place;
        private String note;

        public Builder(@NonNull Sour source, @NonNull Integer submitterId, @NonNull Gedc gedcom, @NonNull String charset) {
            this.source = source;
            this.submitterId = submitterId;
            this.gedcom = gedcom;
            this.charset = charset;
        }

        public Builder withDestination(@NonNull String destination) {
            this.dest = destination;
            return this;
        }

        public Builder withTransmissionDate(@NonNull DateTime transDate) {
            this.transDate = transDate;
            return this;
        }

        public Builder withSubmissinId(@NonNull Integer submissinId) {
            this.submissionId = submissinId;
            return this;
        }

        public Builder withFilename(@NonNull String filename) {
            this.filename = filename;
            return this;
        }

        public Builder withCopyright(@NonNull String copyright) {
            this.copyright = copyright;
            return this;
        }

        public Builder withLanguage(@NonNull String language) {
            this.language = language;
            return this;
        }

        public Builder withPlace(@NonNull String place) {
            this.place = place;
            return this;
        }

        public Builder withNote(@NonNull String note) {
            this.note = note;
            return this;
        }

        public Header build() {
            return new Header(this);
        }
    }
}
