package es.ulpgc.dis.view;

import es.ulpgc.dis.control.CommandFactory;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WorkingDaysService implements Service{
    private final int port;
    private final CommandFactory factory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
    }

    @Override
    public void start() {
        app = Javalin.create()
                .get("/working-date", context -> execute("working-date", context.req(), context.res()))
                .get("/working-days", context -> execute("working-days", context.req(), context.res()))
                .start(port);
    }

    private void execute(String commandName, HttpServletRequest req, HttpServletResponse res) {
        factory.with(req, res).build(commandName).execute();
    }

    @Override
    public void stop() {
        app.stop();
    }
}
