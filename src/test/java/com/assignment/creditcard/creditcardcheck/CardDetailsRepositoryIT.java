/**
 * << Insert CopyRight here >>
 */
package com.assignment.creditcard.creditcardcheck;


import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.creditcard.creditcardcheck.exception.BadRequestException;
import com.assignment.creditcard.creditcardcheck.exception.ElementNotFoundException;
import com.assignment.creditcard.creditcardcheck.model.CardDetails;
import com.assignment.creditcard.creditcardcheck.repository.CardDetailsRepository;

/**
 * Integration test class for {@link CardDetailsRepository}
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CardDetailsRepositoryIT {
	
	@Autowired
	private CardDetailsRepository cardDetailsRepository;
	
	@Test
	public void testSaveCardDetails() throws Exception {
		//Given
		CardDetails cardDetails = CardServiceTestUtil.createCard(Long.valueOf("79927398713"));
		//When
		cardDetailsRepository.save(cardDetails);
		//Then
		CardDetails cardRetrieved = cardDetailsRepository.findById("79927398713").get();
		Assert.assertNotNull("Card details not found", cardRetrieved.getCardNumber());
		Assert.assertEquals("Card details not matched", "79927398713", cardRetrieved.getCardNumber());
		Assert.assertEquals("Card details not matched", "TestHolder 79927398713", cardRetrieved.getCardHolderName());
		Assert.assertEquals("Card details not matched", Double.valueOf(100), cardRetrieved.getCardLimit());
		Assert.assertEquals("Card details not matched", Double.valueOf(0), cardRetrieved.getBalance());
	}

	
	
	@Test
	public void testGetAllCardDetails() throws Exception {
		//Given
		CardDetails cardDetails1 = CardServiceTestUtil.createCard(Long.valueOf("79927398713"));
		CardDetails cardDetails2 = CardServiceTestUtil.createCard(Long.valueOf("889899"));
		cardDetailsRepository.saveAll(Arrays.asList(cardDetails1, cardDetails2));
		//When
		List<CardDetails> cardsRetrieved = cardDetailsRepository.findAll();
		//Then
		Assert.assertNotNull("No Cards retrieved", cardsRetrieved);
		Assert.assertEquals("Cards count not matched", 2, cardsRetrieved.size());
		Assert.assertEquals("Card details not matched", "79927398713", cardsRetrieved.get(0).getCardNumber());
		Assert.assertEquals("Card details not matched", "TestHolder 79927398713", cardsRetrieved.get(0).getCardHolderName());
		Assert.assertEquals("Card details not matched", Double.valueOf(100), cardsRetrieved.get(0).getCardLimit());
		Assert.assertEquals("Card details not matched", Double.valueOf(0), cardsRetrieved.get(0).getBalance());
	}
	

}
