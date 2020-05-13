package it.marvin_flock.gedcom;

import lombok.NonNull;

public class Addr extends GedcomElement {

    private final String address;
    private final String city;
    private final String state;
    private final String postal;
    private final String country;

    private Addr(Builder builder) {
        this.address = builder.address;
        this.city = builder.city;
        this.state = builder.state;
        this.postal = builder.postal;
        this.country = builder.country;
    }

    public String toString(int level) {
        // mandatory field
        if (address == null) {
            throw new NullPointerException();
        }
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendMultiLineFor("ADDR", address, CONT, LINE_SIZE_60, level, sb);

        appendSimpleStringFor("CITY", city, subLevel, sb);
        appendSimpleStringFor("STAE", state, subLevel, sb);
        appendSimpleStringFor("POST", postal, subLevel, sb);
        appendSimpleStringFor("CTRY", country, subLevel, sb);


        return sb.toString();
    }


    public static class Builder {
        private String address;
        private String city;
        private String state;
        private String postal;
        private String country;

        public Builder() {
            // no mandatory fields
        }

        public Builder withAddress(@NonNull String address) {
            this.address = address;
            return this;
        }

        public Builder withCity(@NonNull String city) {
            this.city = city;
            return this;
        }

        public Builder withState(@NonNull String state) {
            this.state = state;
            return this;
        }

        public Builder withPostal(@NonNull String postal) {
            this.postal = postal;
            return this;
        }

        public Builder withCountry(@NonNull String country) {
            this.country = country;
            return this;
        }

        public Addr build() {
            return new Addr(this);
        }
    }
}
