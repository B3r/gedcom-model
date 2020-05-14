package it.marvin_flock.gedcom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gedc extends GedcomElement {

    private static final String VERSION = "5.5.1";
    private static final String GEDCOM_FORM = "LINEAGE-LINKED";

    public String toString(final int level) {
        final int subLevel = level + 1;
        StringBuilder sb = new StringBuilder();

        appendBlankFor("GEDC", level, sb);
        appendSimpleStringFor("VERS", VERSION, subLevel, sb);
        appendSimpleStringFor("FORM", GEDCOM_FORM, subLevel, sb);

        return sb.toString();
    }
}
