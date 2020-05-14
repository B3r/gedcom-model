package it.marvin_flock.gedcom.structures;


import it.marvin_flock.gedcom.Addr;
import it.marvin_flock.gedcom.GedcomElement;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddressStructure extends GedcomElement {

    private final Addr address;
    private final List<String> phones;
    private final List<String> email;
    private final List<String> fax;
    private final List<String> www;

    public AddressStructure(Builder builder) {
        this.address = builder.address;
        this.phones = builder.phones;
        this.email = builder.email;
        this.fax = builder.fax;
        this.www = builder.www;
    }

    @Override
    public String toString(final int level) {
        final StringBuilder sb = new StringBuilder();

        sb.append(address.toString(level));

        appendMaxThreeFor(phones, "PHON", level, sb);
        appendMaxThreeFor(email, "EMAIL", level, sb);
        appendMaxThreeFor(fax, "FAX", level, sb);
        appendMaxThreeFor(www, "WWW", level, sb);

        return sb.toString();
    }

    public static class Builder {
        private final Addr address;
        private List<String> phones;
        private List<String> email;
        private List<String> fax;
        private List<String> www;

        public Builder(@NonNull Addr address) {
            this.address = address;
        }

        public Builder withPhones(@NonNull List<String> phones) {
            this.phones = phones;
            return this;
        }

        public Builder withEmail(@NonNull List<String> email) {
            this.email = email;
            return this;
        }

        public Builder withFax(@NonNull List<String> fax) {
            this.fax = fax;
            return this;
        }

        public Builder withWWW(@NonNull List<String> www) {
            this.www = www;
            return this;
        }

        public AddressStructure build() {
            return new AddressStructure(this);
        }
    }
}
