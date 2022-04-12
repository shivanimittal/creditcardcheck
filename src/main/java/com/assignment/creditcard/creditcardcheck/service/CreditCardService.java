/**
 * << Insert Copyright here >>
 */
package com.assignment.creditcard.creditcardcheck.service;

import java.util.List;

import com.assignment.creditcard.creditcardcheck.exception.BadRequestException;
import com.assignment.creditcard.creditcardcheck.model.CardDetails;
import com.assignment.creditcard.creditcardcheck.validator.CheckCardNumber;

/**
 * Service Interface to handle the Credit Card details
 *
 */
public interface CreditCardService {
	
	void recordCardDetails( CardDetails cardDetails) throws BadRequestException;
	
	List<CardDetails> getAllCards();

}
