/**
 * << Insert CopyRight here >>
 */
package com.assignment.creditcard.creditcardcheck.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER})
@Constraint(validatedBy = CheckCardNumberValidator.class)
/**
 * Custom Java Annotation to validate the Credit Card Number using Luhn 10 algorithm
 *
 */
public @interface CheckCardNumber {
	String message() default "Invalid Card Number. Please provide a valid value.";
	
	Class<?>[]groups() default {};
	
	Class<? extends Payload>[]payload() default {};
	
}
