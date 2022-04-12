/**
 * << Insert Copyright here >>
 */
package com.assignment.creditcard.creditcardcheck.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assignment.creditcard.creditcardcheck.validator.CheckCardNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Model to hold the details of the credit card
 *
 */
@Entity
@Table(name = "creditcards")
@Valid
public class CardDetails implements Serializable {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = 7098629122646585447L;
	
	/**
	 * The 19 digit card number
	 */
	@Id
	@NotNull
	@Digits(integer =  19, fraction = 0)
	@CheckCardNumber
	private String cardNumber;
	
	/**
	 * The name of the card holder
	 */
	@NotBlank(message="Name must not be blank.")
	@Size(max = 30)
	private String cardHolderName;
	
	/**
	 * Current Balance held on the credit card
	 */
	@Digits(integer =  6, fraction = 2)
	private Double balance;
	
	/**
	 * The assigned credit limit on the card 
	 */
	@NotNull(message = "Limit is mandatory.")
	@DecimalMin(inclusive = true, value = "0.00")
	@Digits(integer =  6, fraction = 2)
	private Double cardLimit;

	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	@JsonProperty
	public Double getBalance() {
		return balance;
	}

	@JsonIgnore
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(Double cardLimit) {
		this.cardLimit = cardLimit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, cardHolderName, cardLimit, cardNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardDetails other = (CardDetails) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(cardHolderName, other.cardHolderName)
				&& Objects.equals(cardLimit, other.cardLimit) && Objects.equals(cardNumber, other.cardNumber);
	}
	
	

}
