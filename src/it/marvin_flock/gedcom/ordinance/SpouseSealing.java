package it.marvin_flock.gedcom.ordinance;

import it.marvin_flock.gedcom.dates.DateValue;
import it.marvin_flock.gedcom.enums.SpouseSealingStatus;
import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SpouseSealing extends Ordinance {
    private SpouseSealingStatus status;

    public SpouseSealing() {
        super();
    }

    public SpouseSealing(SpouseSealingStatus status, DateValue date, String templeCode, String place, List<SourceCitation> sourceCitations, List<NoteStructure> notes) {
        super(date, templeCode, place, sourceCitations, notes);
        if (status != null && date == null) {
            throw new IllegalArgumentException("If status is given, date may not be empty");
        }
        this.status = status;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendBlankFor("SLGS", level, sb);

        if (status != null) {
            appendSimpleStringFor("STAT", status.toString(), subLevel, sb);
            sb.append(date.toString(subLevel + 1));
        }

        sb.append(super.toString(subLevel));

        return sb.toString();
    }
}
