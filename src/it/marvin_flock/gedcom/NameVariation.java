package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.enums.VariationType;
import lombok.NonNull;

public class NameVariation extends GedcomElement {
    private final VariationType variationType;
    private final String name;
    private final String type;
    private final NamePiece namePiece;

    public NameVariation(Builder builder) {
        this.variationType = builder.variationType;
        this.name = builder.name;
        this.type = builder.type;
        this.namePiece = builder.namePiece;
    }

    @Override
    public String toString(int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();
        if(variationType == VariationType.FONE) {
            appendSimpleStringFor("FONE", name, level, sb);
        } else {
            appendSimpleStringFor("ROMN", name, level, sb);
        }
        appendSimpleStringFor("TYPE", type, subLevel, sb);
        if (namePiece != null) {
            sb.append(namePiece.toString(subLevel));
        }
        return sb.toString();
    }

    public static class Builder {
        private final String name;
        private final String type;
        private final VariationType variationType;
        private NamePiece namePiece;

        public Builder(@NonNull VariationType variationType, @NonNull String type, @NonNull String name) {
            this.variationType = variationType;
            this.type = type;
            this.name = name;
        }

        public Builder withNamePiece(@NonNull NamePiece namePiece) {
            this.namePiece = namePiece;
            return this;
        }

        public NameVariation build() {
            return new NameVariation(this);
        }
    }

}
