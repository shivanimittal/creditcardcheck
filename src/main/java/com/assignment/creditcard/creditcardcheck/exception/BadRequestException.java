/**
 * 
 */
package com.assignment.creditcard.creditcardcheck.exception;

/**
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 4381066956822055247L;

	public BadRequestException(String message) {
		super(message);
	}
}