package it.marvin_flock.gedcom;


import it.marvin_flock.gedcom.enums.MediaType;
import lombok.NonNull;

/**
 * TODO embedded form is pointing to MULTIMEDIA_RECORD
 * NEED TO FIGURE OUT HOW NEWER FILE FORMATS ARE COMPATIBLE WITH GEDCOM
 * Allowed formats based on 5.5 Specifications:
 * [ bmp | gif | jpeg | ole | pcx | tiff | wav ]
 */
public class MediaLink extends GedcomElement {
    private final Integer mmRecordId;
    private final String format;
    private final String title;
    private final String fileReference;
    private final MediaType mediaType;

    private MediaLink(Builder builder) {
        this.mmRecordId = builder.mmRecordId;
        this.format = builder.format;
        this.title = builder.title;
        this.fileReference = builder.fileReference;
        this.mediaType = builder.mediaType;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        if (mmRecordId != null) {
            appendReferenceFor("OBJE", mmRecordId, level, sb);
            return sb.toString();
        }

        appendBlankFor("OBJE", level, sb);
        appendSimpleStringFor("FILE", fileReference, subLevel, sb);
        if (format != null) {
            appendSimpleStringFor("FORM", format, subLevel + 1, sb);
            if (mediaType != null) {
                appendSimpleStringFor("MEDI", mediaType.toString().toLowerCase(), subLevel + 2, sb);
            }
        }
        appendSimpleStringFor("TITL", title, subLevel, sb);

        return sb.toString();
    }


    public static class Builder {

        private MediaType mediaType;
        private Integer mmRecordId;
        private String format;
        private String title;
        private String fileReference;

        public Builder(@NonNull Integer multiMediaRecordReferenceId) {
            this.mmRecordId = multiMediaRecordReferenceId;
        }

        public Builder(@NonNull String format, @NonNull String fileReference) {
            this.format = format;
            this.fileReference = fileReference;
        }

        public Builder withTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        public Builder withMediaType(@NonNull MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public MediaLink build() {
            return new MediaLink(this);
        }
    }
}
