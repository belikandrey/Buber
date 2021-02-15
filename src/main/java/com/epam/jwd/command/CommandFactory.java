package com.epam.jwd.command;

public class CommandFactory {
    public static Command command(String command) {
        Command temp = null;
        if (command != null) {
            try {
                temp = CommandType.valueOf(command.toUpperCase().replaceAll("-", "_")).getCommand();
            } catch (IllegalArgumentException e) {
                temp = CommandType.TO_HOME.getCommand();
            }
        }
        return temp != null ? temp : CommandType.TO_HOME.getCommand();
    }
}
