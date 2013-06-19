/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.t_systems.demail.sever.servercommand;

import ru.t_systems.demail.soket.command.Command;
import ru.t_systems.demail.soket.command.Result;

/**
 *
 * @author Dart
 */
public class UnknownCommand implements ServerCommand {

    private Result result;

    public UnknownCommand() {
        result = new Result();
    }

    public Result execute(Command command) {
        result.setHasError(true);
        result.setError("unknow command");
        return result;
    }
}
