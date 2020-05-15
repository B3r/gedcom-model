package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.EventAge;
import it.marvin_flock.gedcom.EventDetail;
import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.dates.GregorianDate;
import it.marvin_flock.gedcom.enums.FamilyEventType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


/**
 * is age of husband and age of wife redundant with EventDetail.Age? Are specifications unclear?
 */
@Getter
@Setter
public class FamilyEventStructure extends GedcomElement {

    private final FamilyEventType type;
    private String eventDescriptor;
    private EventDetail event;
    private EventAge ageOfHusband;
    private EventAge ageOfWife;

    public FamilyEventStructure(Builder builder) {
        this.type = builder.type;
        this.event = builder.event;
        this.ageOfHusband = builder.ageOfHusband;
        this.ageOfWife = builder.ageOfWife;
        this.eventDescriptor = builder.eventDescriptor;
    }

    @Override
    public String toString(int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        if (needToAppendY()) {
            appendSimpleStringFor(type.toString(), "Y", level, sb);
        } else if (type == FamilyEventType.EVEN && eventDescriptor != null) {
            appendSimpleStringFor(type.toString(), eventDescriptor, level, sb);
        } else {
            appendBlankFor(type.toString(), level, sb);
        }

        if (event != null) {

            if (isGregorianDate()) {
                GregorianDate date = ((GregorianDate) event.getDate().getDate());
                if (ageOfHusband != null) {
                    sb.append(subLevel);
                    sb.append(" HUSB");
                    sb.append(fullStop);

                    sb.append(ageOfHusband.toString(date.asDate(), subLevel + 1));
                }
                if (ageOfWife != null) {
                    sb.append(subLevel);
                    sb.append(" WIFE");
                    sb.append(fullStop);

                    sb.append(ageOfWife.toString(date.asDate(), subLevel + 1));
                }
            }
            sb.append(event.toString(subLevel));
        }

        return sb.toString();
    }

    private boolean needToAppendY() {
        // if there is no event, or the event has no place and date, the line needs an 'Y'
        return type == FamilyEventType.MARR && (event == null || event.getDate() == null && event.getPlace() == null);
    }

    private boolean isGregorianDate() {
        // try the age stuff when it is a single date within event
        return event.getDate() != null && event.getDate().getDate() != null && event.getDate().getDate() instanceof GregorianDate;
    }

    public static class Builder {
        private final FamilyEventType type;
        private String eventDescriptor;
        private EventDetail event;
        private EventAge ageOfHusband;
        private EventAge ageOfWife;

        public Builder(@NonNull FamilyEventType type) {
            this.type = type;
        }

        public Builder withEventDetail(@NonNull EventDetail eventDetail) {
            this.event = eventDetail;
            return this;
        }

        public Builder withEventDescriptor(@NonNull String eventDescriptor) {
            this.eventDescriptor = eventDescriptor;
            return this;
        }

        public Builder withHusbandAge(@NonNull EventAge ageOfHusband) {
            this.ageOfHusband = ageOfHusband;
            return this;
        }

        public Builder withWifeAge(@NonNull EventAge ageOfWife) {
            this.ageOfWife = ageOfWife;
            return this;
        }

        public FamilyEventStructure build() {
            return new FamilyEventStructure(this);
        }
    }
}
