package es.ulpgc.dis.view.adapters;

import es.ulpgc.dis.control.commands.WorkingDaysCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

public class WorkingDaysRequestAdapter {
    public WorkingDaysCommand.Input inputFor(HttpServletRequest request) {
        return new WorkingDaysCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(request.getParameter("start"));
            }

            @Override
            public LocalDate end() {
                return LocalDate.parse(request.getParameter("end"));
            }
        };
    }

    public WorkingDaysCommand.Output outputFor(HttpServletResponse response) {
        return days -> {
            try {
                response.getWriter().println(days);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
