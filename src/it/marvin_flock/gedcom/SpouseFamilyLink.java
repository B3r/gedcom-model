package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
@Getter
public class SpouseFamilyLink extends FamilyLink {

    public SpouseFamilyLink(@NonNull Integer familyId, List<NoteStructure> notes) {
        this.familyId = familyId;
        this.notes = notes;
    }

    public SpouseFamilyLink(@NonNull Integer familyId) {
        this.familyId = familyId;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendReferenceFor("FAMS", familyId, level, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        return sb.toString();
    }
}
