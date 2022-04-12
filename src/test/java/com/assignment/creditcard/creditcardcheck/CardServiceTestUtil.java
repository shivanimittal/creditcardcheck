/**
 * << Insert CopyRight here>>
 */
package com.assignment.creditcard.creditcardcheck;

import com.assignment.creditcard.creditcardcheck.model.CardDetails;

/**
 * Test Util class to hold the utility functions
 *
 */
public class CardServiceTestUtil {

	public static CardDetails createCard(Long cardNumber) {
		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardNumber(String.valueOf(cardNumber));
		cardDetails.setCardHolderName("TestHolder "+String.valueOf(cardNumber));
		cardDetails.setCardLimit(100.00);
		cardDetails.setBalance(0.00);
		return cardDetails;
	}
}
