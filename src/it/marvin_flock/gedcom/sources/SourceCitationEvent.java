package it.marvin_flock.gedcom.sources;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.enums.EventAttribute;
import it.marvin_flock.gedcom.enums.Role;
import lombok.Getter;

@Getter
public class SourceCitationEvent extends GedcomElement {
    private final EventAttribute type;
    private Role role;
    private String ownDescriptor;

    public SourceCitationEvent(EventAttribute type, String ownDescriptor) {
        this.type = type;
        this.ownDescriptor = ownDescriptor;
    }

    public SourceCitationEvent(EventAttribute type, Role role) {
        this.type = type;
        this.role = role;
    }

    @Override
    public String toString(int level) {
        final StringBuilder sb = new StringBuilder();

        appendSimpleStringFor("EVEN", type.toString(), level, sb);
        if (role != null) {
            appendSimpleStringFor("ROLE", role.toString(), level + 1, sb);
        } else if (ownDescriptor != null) {
            appendSimpleStringFor("ROLE", ownDescriptor, level + 1, sb);
        }
        return sb.toString();
    }
}
