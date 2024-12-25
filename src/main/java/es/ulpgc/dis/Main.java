package es.ulpgc.dis;

import es.ulpgc.dis.control.CommandFactory;
import es.ulpgc.dis.control.commands.WorkingDateCommand;
import es.ulpgc.dis.control.commands.WorkingDaysCommand;
import es.ulpgc.dis.view.WorkingDaysService;
import es.ulpgc.dis.view.adapters.WorkingDateRequestAdapter;
import es.ulpgc.dis.view.adapters.WorkingDaysRequestAdapter;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        factory.register("working-date", WorkingDateBuilder());
        factory.register("working-days", WorkingDaysBuilder());
        new WorkingDaysService(7070, factory).start();
    }

    private static CommandFactory.Builder WorkingDaysBuilder() {
        return (request, response) -> {
            WorkingDaysCommand.Input input = new WorkingDaysRequestAdapter().inputFor(request);
            WorkingDaysCommand.Output output = new WorkingDaysRequestAdapter().outputFor(response);
            return new WorkingDaysCommand(input, output);
        };
    }

    private static CommandFactory.Builder WorkingDateBuilder() {
        return (request, response) -> {
            WorkingDateCommand.Input input = new WorkingDateRequestAdapter().inputFor(request);
            WorkingDateCommand.Output output = new WorkingDateRequestAdapter().outputFor(response);
            return new WorkingDateCommand(input, output);
        };
    }
}