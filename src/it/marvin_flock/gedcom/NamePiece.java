package it.marvin_flock.gedcom;

import it.marvin_flock.gedcom.sources.SourceCitation;
import it.marvin_flock.gedcom.structures.NoteStructure;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class NamePiece extends GedcomElement {
    private final String prefix;
    private final String given;
    private final String nick;
    private final String surnamePrefix;
    private final String surname;
    private final String suffix;

    private final List<SourceCitation> citations;
    private final List<NoteStructure> notes;

    public NamePiece(Builder builder) {
        this.prefix = builder.prefix;
        this.given = builder.given;
        this.nick = builder.nick;
        this.surnamePrefix = builder.surnamePrefix;
        this.surname = builder.surname;
        this.suffix = builder.suffix;
        this.citations = builder.citations;
        this.notes = builder.notes;
    }

    @Override
    public String toString(int level) {
        final StringBuilder sb = new StringBuilder();
        appendSimpleStringFor("NPFX", prefix, level, sb);
        appendSimpleStringFor("GIVN", given, level, sb);
        appendSimpleStringFor("NICK", nick, level, sb);
        appendSimpleStringFor("SPFX", surnamePrefix, level, sb);
        appendSimpleStringFor("SURN", surname, level, sb);
        appendSimpleStringFor("NSFX", suffix, level, sb);

        if (notes != null) {
            notes.forEach(note -> sb.append(note.toString(level)));
        }

        if (citations != null) {
            citations.forEach(sourceCitation -> sb.append(sourceCitation.toString(level)));
        }
        return sb.toString();
    }

    public static class Builder {
        private final String surname;
        private final String given;
        private String prefix;
        private String nick;
        private String surnamePrefix;
        private String suffix;
        private List<SourceCitation> citations;
        private List<NoteStructure> notes;

        public Builder(String surname, String given) {
            if (surname == null && given == null) {
                throw new NullPointerException("At least one of following fields need to be set: surname, given");
            }
            this.surname = surname;
            this.given = given;
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
            this.surnamePrefix = prefix;
            return this;
        }

        public Builder withSuffix(@NonNull String suffix) {
            this.suffix = suffix;
            return this;
        }

        public Builder withSourceCitations(@NonNull List<SourceCitation> citations) {
            this.citations = citations;
            return this;
        }

        public Builder withNotes(@NonNull List<NoteStructure> notes) {
            this.notes = notes;
            return this;
        }

        public NamePiece build() {
            return new NamePiece(this);
        }
    }
}
