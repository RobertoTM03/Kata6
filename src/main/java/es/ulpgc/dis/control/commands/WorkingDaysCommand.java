package es.ulpgc.dis.control.commands;

import es.ulpgc.dis.control.Command;
import es.ulpgc.dis.model.Calendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDaysCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDaysCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        output.workingDays(calculateWorkingDays());
    }

    private int calculateWorkingDays() {
        Iterator<LocalDate> iterator = new Calendar().iteratorFrom(input.start());
        LocalDate result = input.start();
        int workingDays = 0;
        while (result.isBefore(input.end())) {
            workingDays++;
            result = iterator.next();
        }
        return workingDays;
    }

    public interface Input {
        LocalDate start();

        LocalDate end();
    }

    public interface Output {
        void workingDays(int days);
    }
}
