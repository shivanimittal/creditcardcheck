/**
 * << Insert CopyRight here >>
 */
package com.assignment.creditcard.creditcardcheck.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import com.assignment.creditcard.creditcardcheck.exception.BadRequestException;
import com.assignment.creditcard.creditcardcheck.model.CardDetails;
import com.assignment.creditcard.creditcardcheck.repository.CardDetailsRepository;
import com.assignment.creditcard.creditcardcheck.validator.RecordValidatorGroup;

/**
 * Service Implementation for {@link CreditCardService}
 *
 */
@Service
public class CreditCardServiceimpl implements CreditCardService {
	
	@Autowired
	private CardDetailsRepository cardDetailsRepository;
	
	@Override
	public void recordCardDetails(CardDetails cardDetails) throws BadRequestException {
		
		Optional<CardDetails> existingCard = cardDetailsRepository.findById(cardDetails.getCardNumber());
		if(existingCard.isPresent()) {
			throw new BadRequestException("Provided Card Details already exist.");
		}
		cardDetails.setBalance(0.00);
		cardDetailsRepository.save(cardDetails);
	}

	@Override
	public List<CardDetails> getAllCards() {
		List<CardDetails> allCards = cardDetailsRepository.findAll();
		return CollectionUtils.isEmpty(allCards) ? Collections.emptyList() : allCards;
	}

}
