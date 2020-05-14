package it.marvin_flock.gedcom.records;

import it.marvin_flock.gedcom.GedcomElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Record extends GedcomElement {
    protected final int id;

    public Record(int id) {
        this.id = id;
    }
}
