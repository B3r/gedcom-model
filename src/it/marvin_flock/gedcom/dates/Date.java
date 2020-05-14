package it.marvin_flock.gedcom.dates;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Date {
    private boolean isComplete;

    protected void setComplete(boolean isComplete){
        this.isComplete = isComplete;
    };

    public abstract LocalDate asDate();
    public abstract String toString();
}
