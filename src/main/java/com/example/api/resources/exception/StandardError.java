package com.example.api.resources.exception;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String message;
	private Instant data;

	public StandardError(Integer status, String message, Instant data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

}
