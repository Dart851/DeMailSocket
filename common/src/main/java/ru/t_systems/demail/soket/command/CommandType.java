package ru.t_systems.demail.soket.command;

public enum CommandType {

	USER_LOGIN("UserDTO"),
	USER_REGISTRATION("UserDTO"),
	QUIT("quit"),
	DELETE_LABEL("LabalDTO"),
        RENAME_LABEL("LabelDTO+String"),
        GET_MESSAGE("<Set>StatusDTO"),
        GET_SEND_MESSAGE("<Set>StatusDTO"),
        SEND_MESSAGE("MessageDTO");
        private String typeValue;

	private CommandType(String type) {
		typeValue = type;
	}
}
