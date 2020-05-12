package it.marvin_flock.gedcom.dates;

import it.marvin_flock.gedcom.enums.DateType;
import lombok.NonNull;

public class DateRange implements IDate {

    private final DateType type;

    private final IDate earlyDate;
    private IDate lateDate;

    public DateRange(@NonNull DateType type, @NonNull IDate earlyDate) {
        this.type = type;
        this.earlyDate = earlyDate;
    }

    public DateRange(@NonNull DateType type, @NonNull IDate earlyDate, IDate lateDate) {
        this.type = type;
        this.earlyDate = earlyDate;
        this.lateDate = lateDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(type.name());
        sb.append(" ");
        sb.append(earlyDate.toString());

        if (type == DateType.BET) {
            sb.append(" ");
            sb.append(lateDate);
        }

        return sb.toString();
    }
}
