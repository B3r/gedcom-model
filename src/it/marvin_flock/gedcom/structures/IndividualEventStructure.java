package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.EventDetail;
import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.enums.AdoptedBy;
import it.marvin_flock.gedcom.enums.IndividualEventType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class IndividualEventStructure extends GedcomElement {

    private final IndividualEventType type;
    private final EventDetail event;
    private AdoptedBy adoptedBy;
    private Integer familyId;

    // usable for BIRT,CHR,ADOP events
    public IndividualEventStructure(@NonNull IndividualEventType type, EventDetail event, Integer familyId) {
        this.type = type;
        this.event = event;
        this.familyId = familyId;
    }

    // usable for ADOP events
    public IndividualEventStructure(@NonNull IndividualEventType type, EventDetail event, AdoptedBy adoptedBy, Integer familyId) {
        this.type = type;
        this.event = event;
        this.adoptedBy = adoptedBy;
        this.familyId = familyId;
    }

    // used for all other events
    public IndividualEventStructure(@NonNull IndividualEventType type, EventDetail event) {
        this.type = type;
        this.event = event;
    }

    @Override
    public String toString(final int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        // if there is no event, or the event has no place and date, the line needs an 'Y'
        if (type != IndividualEventType.EVEN && (event == null || event.getDate() == null && event.getPlace() == null)) {
            appendSimpleStringFor(type.toString(), "Y", level, sb);
        } else {
            appendBlankFor(type.toString(), level, sb);
        }

        if (event != null) {
            sb.append(event.toString(subLevel));
        }

        if (familyId != null && (type == IndividualEventType.BIRT || type == IndividualEventType.CHR || type == IndividualEventType.ADOP)) {
            appendReferenceFor("FAMC", familyId, subLevel, sb);
            if (adoptedBy != null && type == IndividualEventType.ADOP) {
                appendSimpleStringFor("ADOP", adoptedBy.toString(), subLevel + 1, sb);
            }
        }
        return sb.toString();
    }
}
