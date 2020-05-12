package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.enums.Pedigree;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.util.List;

public class ChildFamilyLink extends FamilyLink {
    private Pedigree pedigree;

    public ChildFamilyLink(@NonNull Integer familyId) {
        this.familyId = familyId;
    }

    public ChildFamilyLink(@NonNull Integer familyId, Pedigree pedigree, List<NoteStructure> notes) {
        this.familyId = familyId;
        this.pedigree = pedigree;
        this.notes = notes;
    }

    public ChildFamilyLink(@NonNull Integer familyId, Pedigree pedigree) {
        this.familyId = familyId;
        this.pedigree = pedigree;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendReferenceFor("FAMC", familyId, level, sb);
        if (pedigree != null) {
            appendSimpleStringFor("PEDI", pedigree.toString().toLowerCase(), subLevel, sb);
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }
        return sb.toString();
    }
}
