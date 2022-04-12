/**
 * << Insert CopyRight here>>
 */
package com.assignment.creditcard.creditcardcheck;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.creditcard.creditcardcheck.exception.BadRequestException;
import com.assignment.creditcard.creditcardcheck.model.CardDetails;
import com.assignment.creditcard.creditcardcheck.repository.CardDetailsRepository;
import com.assignment.creditcard.creditcardcheck.service.CreditCardService;
import com.assignment.creditcard.creditcardcheck.service.CreditCardServiceimpl;


/**
 * JUnit tests for {@link CreditCardServiceimpl}
 *
 */
@RunWith(SpringRunner.class)
public class CreditCardServiceimplTest {

	@TestConfiguration
	static class CreditCardServiceTestContextConfiguration {

		@Bean
		public CreditCardService creditCardService() {
			return new CreditCardServiceimpl();
		}
	}

	@Autowired
	private CreditCardService creditCardService;

	@MockBean
	private CardDetailsRepository cardDetailsRepository;
	
	private CardDetails cardDetails;
	
	@Before
	public void setUp() {
		cardDetails = CardServiceTestUtil.createCard(Long.valueOf("79927398713"));
	}
	
	@Test(expected = BadRequestException.class)
	public void testSaveCardDetailsForExistingCard() throws Exception {
		Mockito.when(cardDetailsRepository.findById("79927398713")).thenReturn(Optional.of(cardDetails));
		creditCardService.recordCardDetails(cardDetails);
	}
	
	@Test
	public void testGetAllCardDetails() throws Exception {
		Mockito.when(cardDetailsRepository.findAll()).thenReturn(Arrays.asList(cardDetails));
		List<CardDetails> allCards = creditCardService.getAllCards();
		Assert.assertNotNull(allCards);
		Assert.assertEquals("List size does not match", 1, allCards.size());
	}
	
	@Test
	public void testGetAllCardDetailsWithNoCards() throws Exception {
		Mockito.when(cardDetailsRepository.findAll()).thenReturn(null);
		List<CardDetails> allCards = creditCardService.getAllCards();
		Assert.assertNotNull(allCards);
		Assert.assertEquals("List size does not match", 0, allCards.size());
	}
}
