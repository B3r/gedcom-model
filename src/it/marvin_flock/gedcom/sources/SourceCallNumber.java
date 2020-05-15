package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.enums.MediaType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SourceCallNumber extends GedcomElement {

    private final String callNumber;
    private MediaType mediaType;

    public SourceCallNumber(@NonNull String callNumber, MediaType mediaType) {
        this.callNumber = callNumber;
        this.mediaType = mediaType;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("CALN", callNumber, level, sb);

        if (mediaType != null) {
            appendSimpleStringFor("MEDI", mediaType.toString().toLowerCase(), subLevel, sb);
        }

        return sb.toString();
    }
}
