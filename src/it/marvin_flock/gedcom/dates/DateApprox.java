package it.marvin_flock.gedcom.dates;

import it.marvin_flock.gedcom.enums.ApproxType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DateApprox implements IDate {

    private ApproxType type;
    private IDate date;

    public DateApprox(@NonNull ApproxType type, @NonNull IDate date) {
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return type.name() +
                " " +
                date.toString();
    }
}
