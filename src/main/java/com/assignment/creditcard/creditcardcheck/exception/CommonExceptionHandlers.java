package com.assignment.creditcard.creditcardcheck.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.assignment.creditcard.creditcardcheck.controller.CreditCardController;

@ControllerAdvice(assignableTypes = { CreditCardController.class })
public class CommonExceptionHandlers {
	
	@ExceptionHandler({ BadRequestException.class }) 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse handleBadRequestException(BadRequestException ex,
			HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setStatus(400);
		error.setMessages(Arrays.asList(ex.getMessage()));
		return error;
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class }) 
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}

	private ExceptionResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		ExceptionResponse error = new ExceptionResponse();
		error.setStatus(400);
		error.setMessages(new ArrayList<String>());
		for (org.springframework.validation.FieldError fieldError: fieldErrors) {
			error.getMessages().add(fieldError.getDefaultMessage());
		}
		return error;
	}
}
