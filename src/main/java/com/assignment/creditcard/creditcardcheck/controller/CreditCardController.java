/**
 * << Insert CopyRight here >>
 */
package com.assignment.creditcard.creditcardcheck.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.creditcard.creditcardcheck.exception.BadRequestException;
import com.assignment.creditcard.creditcardcheck.exception.ExceptionResponse;
import com.assignment.creditcard.creditcardcheck.model.CardDetails;
import com.assignment.creditcard.creditcardcheck.service.CreditCardService;

/**
 * Rest Controller to expose the Credit Card endpoints
 *
 */
@RestController
@RequestMapping(path = "/v1/creditcards")
@CrossOrigin(origins = "http://localhost:3000")
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;

	@PostMapping(path = "/add", consumes = "application/json")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public void recordCardDetails(@Valid @RequestBody CardDetails cardDetails) throws BadRequestException {
		creditCardService.recordCardDetails(cardDetails);
	}


	@GetMapping(path = "/getAll", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<CardDetails> getAllCards() {
		return creditCardService.getAllCards();
	}


	
}
