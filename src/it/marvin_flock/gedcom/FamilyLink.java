package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public abstract class FamilyLink extends GedcomElement {
    protected Integer familyId;
    protected List<NoteStructure> notes;
}
