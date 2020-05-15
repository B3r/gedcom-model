package it.marvin_flock.gedcom.ordinance;

import it.marvin_flock.gedcom.dates.DateValue;
import it.marvin_flock.gedcom.enums.ChildSealingStatus;
import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChildSealOrdinance extends Ordinance {
    private ChildSealingStatus status;
    private Integer familyReference;

    public ChildSealOrdinance(@NonNull Integer familyReferenceId) {
        this.familyReference = familyReferenceId;
    }

    public ChildSealOrdinance(ChildSealingStatus status, @NonNull Integer familyReferenceId, DateValue date, String templeCode, String place, List<SourceCitation> sourceCitations, List<NoteStructure> notes) {
        super(date, templeCode, place, sourceCitations, notes);
        if (status != null && date == null) {
            throw new IllegalArgumentException("If status is given, date may not be empty");
        }
        this.status = status;
        this.familyReference = familyReferenceId;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendBlankFor("SLGC", level, sb);
        appendReferenceFor("FAMC", familyReference, subLevel, sb);

        if (status != null) {
            appendSimpleStringFor("STAT", status.toString(), subLevel, sb);
            sb.append(date.toString(subLevel + 1));
        }

        sb.append(super.toString(subLevel));

        return sb.toString();
    }
}
