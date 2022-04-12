/**
 * << Insert Copyright here >>
 */
package com.assignment.creditcard.creditcardcheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.creditcard.creditcardcheck.model.CardDetails;

/**
 * JPA Repository for handling the {@link CardDetails}
 *
 */
@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails, String> {

}
