/**
 * << Insert CopyRight here >>
 */
package com.assignment.creditcard.creditcardcheck.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.assignment.creditcard.creditcardcheck.model.CardDetails;

/**
 * Custom Java Annotation to validate the Credit Card Number using Luhn 10 algorithm
 *
 */
public class CheckCardNumberValidator implements ConstraintValidator<CheckCardNumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return checkLuhn(value);
	}

	private boolean checkLuhn(String cardNumber)
	{
	    int nDigits = cardNumber.length();
	 
	    int nSum = 0;
	    boolean isSecond = false;
	    for (int i = nDigits - 1; i >= 0; i--)
	    {
	        int d = cardNumber.charAt(i) - '0';
	 
	        if (isSecond == true)
	            d = d * 2;
	 
	        // We add two digits to handle
	        // cases that make two digits
	        // after doubling
	        nSum += d / 10;
	        nSum += d % 10;
	 
	        isSecond = !isSecond;
	    }
	    return (nSum % 10 == 0);
	}
}
