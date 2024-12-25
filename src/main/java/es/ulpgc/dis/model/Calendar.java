package es.ulpgc.dis.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import static java.time.DayOfWeek.*;

public class Calendar {
    public Iterable<LocalDate> from(LocalDate date) {
        return () -> iteratorFrom(date);
    }

    public Iterator<LocalDate> iteratorFrom(LocalDate date) {
        return new Iterator<LocalDate>() {
            LocalDate current = date;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public LocalDate next() {
                LocalDate result = current.plusDays(1);
                while (!isWorkingDate(result)) result = result.plusDays(1);
                current = result;
                return result;
            }
        };
    }

    Set<DayOfWeek> workingDays = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);

    private boolean isWorkingDate(LocalDate date) {
        return workingDays.contains(date.getDayOfWeek());
    }
}
