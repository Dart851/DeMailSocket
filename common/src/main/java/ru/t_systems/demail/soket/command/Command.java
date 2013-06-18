package ru.t_systems.demail.soket.command;

import java.io.Serializable;

public class Command implements Serializable {

	private CommandType commandName;

	private Object commandSource;

	public Command(CommandType commandName, Object commandSource) {
		this.commandName = commandName;
		this.commandSource = commandSource;
	}

	public CommandType getCommandName() {
		return commandName;
	}

	public void setCommandName(CommandType commandName) {
		this.commandName = commandName;
	}

	public Object getCommandSource() {
		return commandSource;
	}

	public void setCommandSource(Object commandSource) {
		this.commandSource = commandSource;
	}
}
