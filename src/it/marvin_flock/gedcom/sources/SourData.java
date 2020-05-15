package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.dates.DateExact;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SourData extends GedcomElement {

    private String name;
    private DateExact date;
    private String copyright;

    public SourData(Builder builder) {
        this.name = builder.name;
        this.date = builder.date;
        this.copyright = builder.copyright;
    }

    public String toString(int level) {
        if (name == null) {
            throw new NullPointerException("Name may not be empty");
        }
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("DATA", name, level, sb);

        if (date != null) {
            sb.append(date.toString(subLevel));
        }

        appendMultiLineFor("COPR", copyright, CONT, LINE_SIZE_90, subLevel, sb);

        return sb.toString();
    }

    public static class Builder {
        private String name;
        private DateExact date;
        private String copyright;

        public Builder() {
            // no mandatory fields
        }

        public Builder withName(@NonNull String name) {
            this.name = name;
            return this;
        }

        public Builder withDate(@NonNull DateExact date) {
            this.date = date;
            return this;
        }

        public Builder withCopyright(@NonNull String copyright) {
            this.copyright = copyright;
            return this;
        }

        public SourData build() {
            return new SourData(this);
        }
    }
}
