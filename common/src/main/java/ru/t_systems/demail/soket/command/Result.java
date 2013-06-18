package ru.t_systems.demail.soket.command;

import java.io.Serializable;

public class Result implements Serializable {

	private boolean hasError = false;
	private String error = "";
	private Object result = null;

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
