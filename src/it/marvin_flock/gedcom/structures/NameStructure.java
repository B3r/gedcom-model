package it.marvin_flock.gedcom.structures;

import it.marvin_flock.gedcom.GedcomElement;
import it.marvin_flock.gedcom.sources.SourceCitationNotable;
import lombok.NonNull;

import java.util.List;

public class NameStructure extends GedcomElement {

    private final String lastName;
    private final String firstName;
    private final String prefix;
    private final String nick;
    private final String lastNamePrefix;
    private final String suffix;
    private final List<SourceCitationNotable> citations;
    private final List<NoteStructure> notes;

    public NameStructure(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.prefix = builder.prefix;
        this.nick = builder.nick;
        this.lastNamePrefix = builder.lastNamePrefix;
        this.suffix = builder.suffix;
        this.citations = builder.citations;
        this.notes = builder.notes;
    }

    @Override
    public String toString(int level) {
        final int subLevel = level + 1;
        final StringBuilder sb = new StringBuilder();

        sb.append(level);
        sb.append(" NAME ");

        if (firstName != null) {
            sb.append(firstName);
        }

        if (firstName != null && lastName != null) {
            sb.append(" ");
        }

        if (lastName != null) {
            sb.append("/");
            sb.append(lastName);
            sb.append("/");
        }
        sb.append(fullStop);

        appendSimpleStringFor("NPFX", prefix, subLevel, sb);
        appendSimpleStringFor("GIVN", firstName, subLevel, sb);
        appendSimpleStringFor("NICK", nick, subLevel, sb);
        appendSimpleStringFor("SPFX", lastNamePrefix, subLevel, sb);
        appendSimpleStringFor("SURN", lastName, subLevel, sb);
        appendSimpleStringFor("NSFX", suffix, subLevel, sb);

        if (citations != null) {
            citations.forEach(sourceCitation -> sb.append(sourceCitation.toString(subLevel)));
        }

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(subLevel)));
        }

        return sb.toString();
    }

    public static class Builder {
        private final String lastName;
        private final String firstName;
        private String prefix;
        private String nick;
        private String lastNamePrefix;
        private String suffix;
        private List<SourceCitationNotable> citations;
        private List<NoteStructure> notes;

        public Builder(String lastName, String firstName) {
            if (lastName == null && firstName == null) {
                throw new NullPointerException("At least one of following fields need to be set: firstName, lastName");
            }
            this.lastName = lastName;
            this.firstName = firstName;
        }

        public Builder withPrefix(@NonNull String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder withNickname(@NonNull String nickname) {
            this.nick = nickname;
            return this;
        }

        public Builder withLastNamePrefix(@NonNull String prefix) {
            this.lastNamePrefix = prefix;
            return this;
        }

        public Builder withSuffix(@NonNull String suffix) {
            this.suffix = suffix;
            return this;
        }

        public Builder withSourceCitations(@NonNull List<SourceCitationNotable> citations) {
            this.citations = citations;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public NameStructure build() {
            return new NameStructure(this);
        }
    }
}
