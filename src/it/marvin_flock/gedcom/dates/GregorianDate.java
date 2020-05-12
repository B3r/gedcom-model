package it.marvin_flock.gedcom.dates;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * check leapyears
 * check end of month, 28/29/30/31
 * Why own implementation of a date? Because of Partial dates
 * Set complete, when all three fields are provided
 */
@Getter
@Setter
public class GregorianDate extends Date implements IDate {

    private Integer year;
    private Integer month;
    private Integer day;

    public GregorianDate() {
    }

    public GregorianDate(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.setComplete(year != null && month != null && day != null);
    }

    @Override
    public LocalDate asDate() {
        if (!isComplete()) {
            throw new NullPointerException("Date parsing not possible, due to incomplete date parts");
        }
        String date = "" + year + month + day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, formatter);

    }

    /**
     * @return a date string like dd MMM yyyy, e.g. 12 dec 1905
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (day != null) {
            sb.append(day);
        }

        if (month != null) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            String monthStr = new DateFormatSymbols(Locale.ENGLISH).getShortMonths()[month - 1].toUpperCase();
            sb.append(monthStr);
        }

        if (year != null) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(year);
        }

        return sb.toString();
    }
}
