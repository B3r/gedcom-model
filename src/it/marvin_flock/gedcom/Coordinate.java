package it.marvin_flock.gedcom;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Coordinate extends GedcomElement {

    private final String lat;
    private final String lng;

    public Coordinate(@NonNull String lat, @NonNull String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        StringBuilder sb = new StringBuilder();

        appendBlankFor("MAP", level, sb);
        appendSimpleStringFor("LATI", lat, subLevel, sb);
        appendSimpleStringFor("LONG", lng, subLevel, sb);

        return sb.toString();
    }
}
