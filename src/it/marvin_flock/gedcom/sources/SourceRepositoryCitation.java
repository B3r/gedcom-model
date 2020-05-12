package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.NonNull;

import java.util.List;

public class SourceRepositoryCitation extends GedcomElement {
    private final Integer repositoryId;
    private List<NoteStructure> notes;
    private List<SourceCallNumber> callNumbers;

    public SourceRepositoryCitation(@NonNull Integer repositoryId, List<NoteStructure> notes, List<SourceCallNumber> callNumbers) {
        this.repositoryId = repositoryId;
        this.notes = notes;
        this.callNumbers = callNumbers;
    }

    public SourceRepositoryCitation(@NonNull Integer repositoryId, List<SourceCallNumber> callNumbers) {
        this.repositoryId = repositoryId;
        this.callNumbers = callNumbers;
    }

    public SourceRepositoryCitation(@NonNull Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        appendReferenceFor("REPO", repositoryId, level, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        if (callNumbers != null) {
            callNumbers.forEach(callNumber -> sb.append(callNumber.toString(subLevel)));
        }
        return sb.toString();
    }
}
