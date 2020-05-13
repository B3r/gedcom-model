package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.dates.DateValue;
import lombok.Getter;

import java.util.List;
@Getter
public class SourceCitationData extends GedcomElement {

    private DateValue date;
    private List<String> texts;

    public SourceCitationData() {
    }

    public SourceCitationData(DateValue date) {
        this.date = date;
    }

    public SourceCitationData(List<String> texts) {
        this.texts = texts;
    }

    public SourceCitationData(DateValue date, List<String> texts) {
        this.date = date;
        this.texts = texts;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendBlankFor("DATA", level, sb);

        if (date != null) {
            sb.append(date.toString(subLevel));
        }

        if (texts != null) {
            texts.forEach(text -> appendMultiLineFor("TEXT", text, CONT, LINE_SIZE_248, subLevel, sb));
        }
        return sb.toString();
    }
}
