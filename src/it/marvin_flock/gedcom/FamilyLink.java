package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.structures.NoteStructure;

import java.util.List;

public abstract class FamilyLink extends GedcomElement {
    protected Integer familyId;
    protected List<NoteStructure> notes;
}
