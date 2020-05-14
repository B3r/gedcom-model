package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.structures.AddressStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Sour extends GedcomElement {

    private final String approvedSystemId;
    private final String version;
    private final String productName;
    private final String businessName;
    private final AddressStructure address;
    private final SourData data;

    private Sour(Builder builder) {
        this.approvedSystemId = builder.approvedSystemId;
        this.version = builder.version;
        this.productName = builder.productName;
        this.businessName = builder.businessName;
        this.address = builder.address;
        this.data = builder.data;
    }

    public String toString(int level) {
        if (approvedSystemId == null) {
            throw new NullPointerException("Approved System Id may not be empty");
        }
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("SOUR", approvedSystemId, level, sb);
        appendSimpleStringFor("VERS", version, subLevel, sb);
        appendSimpleStringFor("NAME", productName, subLevel, sb);
        appendSimpleStringFor("CORP", businessName, subLevel, sb);

        if (address != null) {
            sb.append(address.toString(subLevel + 1));
        }

        if (data != null) {
            sb.append(data.toString(subLevel));
        }

        return sb.toString();
    }

    public static class Builder {
        private final String approvedSystemId;
        private String version;
        private String productName;
        private String businessName;
        private AddressStructure address;
        private SourData data;

        public Builder(@NonNull String approvedSystemId) {
            this.approvedSystemId = approvedSystemId;
        }

        public Builder withVersion(@NonNull String version) {
            this.version = version;
            return this;
        }

        public Builder withProductName(@NonNull String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withBusinessName(@NonNull String businessName) {
            this.businessName = businessName;
            return this;
        }

        public Builder withAddress(@NonNull AddressStructure address) {
            this.address = address;
            return this;
        }

        public Builder withSourceData(@NonNull SourData data) {
            this.data = data;
            return this;
        }

        public Sour build() {
            return new Sour(this);
        }
    }
}
