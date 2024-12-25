package es.ulpgc.dis.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    Map<String, Builder> builders = new HashMap<>();

    public void register(String commandName, Builder builder) {
        builders.put(commandName, builder);
    }

    public Executor with(HttpServletRequest request, HttpServletResponse response) {
        return name -> builders.get(name).build(request, response);
    }

    public interface Builder {
        Command build(HttpServletRequest request, HttpServletResponse response);
    }

    public interface Executor {
        Command build(String name);
    }
}
