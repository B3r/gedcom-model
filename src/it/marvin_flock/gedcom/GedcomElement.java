package it.marvin_flock.gedcom;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
@Setter
public abstract class GedcomElement {
    protected static final String CONT = "CONT";
    protected static final int LINE_SIZE_60 = 60;
    protected static final int LINE_SIZE_90 = 90;
    protected static final int LINE_SIZE_248 = 248;
    protected static final int LINE_SIZE_255 = 255;

    protected final String fullStop = System.lineSeparator();

    protected String asXRef(int id) {
        return "@" + id + "@";
    }

    protected static List<String> splitString(String msg, int lineSize) {
        // split String on lineSize, preferably for whitespaces
        // regex is not working, dont know why: (?<=\\G.{"+ (lineSize - 8) +"}\\s)
        String[] words = msg.split(" ");
        List<String> parts = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            // plus 1 for space, if part is not empty
            boolean isLegit = line.length() == 0 && word.length() < lineSize || line.length() + word.length() + 1 < lineSize;
            if (isLegit) {
                line.append(StringUtils.isBlank(line.toString()) ? word : " " + word);
            } else {
                // finish current line
                parts.add(line.toString());

                // if the word itself is larger than linesize
                if (word.length() >= lineSize) {
                    List<String> subList = Arrays.asList(word.split(".{" + lineSize + "}"));
                    if (!subList.isEmpty()) {
                        parts.addAll(subList);
                        if (parts.get(parts.size() - 1).length() < lineSize) {
                            line = new StringBuilder(parts.get(parts.size() - 1));
                            parts.remove(parts.size() - 1);
                            continue;
                        }
                    }
                }

                // adding the word to part will hit linesize
                line = new StringBuilder(word);
            }
        }
        parts.add(line.toString());

        return parts;
    }

    /**
     * e.g. n DATA
     *
     * @param baseWord
     * @param level
     * @param sb
     */
    protected void appendBlankFor(String baseWord, int level, StringBuilder sb) {
        sb.append(level);
        sb.append(" ");
        sb.append(baseWord);
        sb.append(fullStop);
    }

    /**
     * e.g. n LANG DE
     * n LANG EN
     * n LANG MX
     *
     * @param items
     * @param baseWord
     * @param level
     * @param sb
     */
    protected void appendMaxThreeFor(final List<String> items, final String baseWord, final int level, final StringBuilder sb) {
        if (items != null) {
            items.stream().limit(3).forEach(item -> appendSimpleStringFor(baseWord, item, level, sb));
        }
    }

    /**
     * e.g. n NOTE <Text>
     * +1 [ CONT | CONC ] <TEXT>
     *
     * @param baseWord
     * @param split
     * @param appendWord
     * @param lineSize
     * @param level
     * @param sb
     */
    protected void appendMultiLineFor(String baseWord, String split, String appendWord, final int lineSize, final int level, StringBuilder sb) {
        if (split == null) {
            return;
        }
        final List<String> multilines = splitString(split, lineSize);
        if (!multilines.isEmpty()) {
            final int subLevel = level + 1;
            appendSimpleStringFor(baseWord, multilines.get(0), level, sb);

            multilines.remove(0);
            multilines.forEach(line -> appendSimpleStringFor(appendWord, line, subLevel, sb));
        }
    }

    /**
     * e.g. n OBJE @3@
     *
     * @param baseWord
     * @param id
     * @param level
     * @param sb
     */
    protected void appendReferenceFor(final String baseWord, final Integer id, final int level, final StringBuilder sb) {
        if (id == null) {
            return;
        }
        sb.append(level);
        sb.append(" ");
        sb.append(baseWord);
        sb.append(" ");
        sb.append(asXRef(id));
        sb.append(fullStop);
    }

    /**
     * e.g. n @2@ INDI
     *
     * @param baseWord
     * @param id
     * @param level
     * @param sb
     */
    protected void appendSimpleIdFor(final String baseWord, final int id, final int level, final StringBuilder sb) {
        sb.append(level);
        sb.append(" ");
        sb.append(asXRef(id));
        sb.append(" ");
        sb.append(baseWord);
        sb.append(fullStop);
    }

    /**
     * e.g. n RIN <Text>
     *
     * @param baseWord
     * @param value
     * @param level
     * @param sb
     */
    protected void appendSimpleStringFor(final String baseWord, final String value, final int level, final StringBuilder sb) {
        if (value != null) {
            sb.append(level);
            sb.append(" ");
            sb.append(baseWord);
            sb.append(" ");
            sb.append(value);
            sb.append(fullStop);
        }
    }

    public abstract String toString(final int level);

}
