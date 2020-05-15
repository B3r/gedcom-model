package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.EventDetail;
import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.enums.AttributeType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class IndividualAttributeStructure extends GedcomElement {

    private final AttributeType type;
    private String value;
    private EventDetail event;

    public IndividualAttributeStructure(@NonNull AttributeType type, String value, EventDetail event) {
        this.type = type;
        this.value = value;
        this.event = event;
    }

    public IndividualAttributeStructure(@NonNull AttributeType type, EventDetail event) {
        this.type = type;
        this.event = event;
    }

    @Override
    public String toString(int level) {
        final StringBuilder sb = new StringBuilder();

        if (type == AttributeType.RESI) {
            appendBlankFor("RESI", level, sb);
        } else {
            appendSimpleStringFor(type.toString(), value, level, sb);
        }

        if (event != null) {
            final int subLevel = level + 1;
            sb.append(event.toString(subLevel));
        }
        return sb.toString();
    }
}

