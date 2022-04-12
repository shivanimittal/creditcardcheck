package com.assignment.creditcard.creditcardcheck.exception;

import java.util.List;

public class ExceptionResponse {
	
	Integer status;
	
	List<String> messages;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	} 

	
}
