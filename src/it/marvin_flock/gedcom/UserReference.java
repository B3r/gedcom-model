package it.marvin_flock.gedcom;

import lombok.NonNull;

public class UserReference extends GedcomElement {

    private final String reference;
    private String type;

    public UserReference(@NonNull String reference, String type) {
        this.reference = reference;
        this.type = type;
    }

    public UserReference(@NonNull String reference) {
        this.reference = reference;
    }

    @Override
    public String toString(int level) {
        if (reference == null) {
            throw new NullPointerException("reference may not be empty");
        }
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("REFN", reference, level, sb);

        if (type != null) {
            appendSimpleStringFor("TYPE", reference, subLevel, sb);
        }

        return sb.toString();
    }
}
