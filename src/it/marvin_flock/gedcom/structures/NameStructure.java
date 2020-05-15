package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.NamePiece;
import it.marvin_flock.gedcom.NameVariation;
import it.marvin_flock.gedcom.enums.NameType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class NameStructure extends GedcomElement {

    private final String lastName;
    private final String firstName;
    private NamePiece namePiece;
    private NameType type;
    private String ownType;
    private List<NameVariation> phonetics;
    private List<NameVariation> romanized;

    public NameStructure(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.namePiece = builder.namePiece;
        this.type = builder.type;
        this.ownType = builder.ownType;
        this.phonetics = builder.phonetics;
        this.romanized = builder.romanized;
    }

    @Override
    public String toString(int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        sb.append(level);
        sb.append(" NAME ");

        if (firstName != null) {
            sb.append(firstName);
        }

        if (firstName != null && lastName != null) {
            sb.append(" ");
        }

        if (lastName != null) {
            sb.append("/");
            sb.append(lastName);
            sb.append("/");
        }
        sb.append(fullStop);

        if (type != null) {
            appendSimpleStringFor("TYPE", type.toString().toLowerCase(), subLevel, sb);
        } else if (ownType != null) {
            appendSimpleStringFor("TYPE", ownType, subLevel, sb);
        }

        if (namePiece != null) {
            sb.append(namePiece.toString(subLevel));
        }

        if (phonetics != null) {
            phonetics.forEach(phonetic -> sb.append(phonetic.toString(subLevel)));
        }

        if (romanized != null) {
            romanized.forEach(roman -> sb.append(roman.toString(subLevel)));
        }

        return sb.toString();
    }

    public static class Builder {
        private final String lastName;
        private final String firstName;
        private List<NameVariation> phonetics;
        private List<NameVariation> romanized;
        private String ownType;
        private NameType type;
        private NamePiece namePiece;

        public Builder(String lastName, String firstName) {
            if (lastName == null && firstName == null) {
                throw new NullPointerException("At least one of following fields need to be set: firstName, lastName");
            }
            this.lastName = lastName;
            this.firstName = firstName;
        }

        public Builder withType(@NonNull NameType nameType) {
            this.type = nameType;
            return this;
        }

        public Builder withOwnType(@NonNull String ownType) {
            this.ownType = ownType;
            return this;
        }

        public Builder withNamePiece(@NonNull NamePiece namePiece) {
            this.namePiece = namePiece;
            return this;
        }

        public Builder withPhonetics(@NonNull List<NameVariation> phonetics) {
            this.phonetics = phonetics;
            return this;
        }

        public Builder withRomanized(@NonNull List<NameVariation> romanized) {
            this.romanized = romanized;
            return this;
        }

        public NameStructure build() {
            return new NameStructure(this);
        }
    }
}
