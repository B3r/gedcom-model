package it.marvin_flock.gedcom;

import lombok.Getter;
import lombok.NonNull;
@Getter
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
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("REFN", reference, level, sb);

        if (type != null) {
            appendSimpleStringFor("TYPE", reference, subLevel, sb);
        }

        return sb.toString();
    }
}
