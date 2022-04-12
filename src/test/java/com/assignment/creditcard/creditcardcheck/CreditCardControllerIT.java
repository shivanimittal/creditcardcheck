/**
 * << Insert CopyRight here >>
 */
package com.assignment.creditcard.creditcardcheck;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.creditcard.creditcardcheck.controller.CreditCardController;

/**
 * Integration tests for {@link CreditCardController}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.MOCK, classes = CreditcardcheckApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CreditCardControllerIT {
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	@Transactional
	public void testAddCardWithValidCard() throws Exception {
		mvc.perform(post("/v1/creditcards/add")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"cardNumber\": \"79927398713\",\"cardHolderName\": \"Test One\",\"cardLimit\": \"5000.00\"}"))
			      .andExpect(status().isCreated());
	}
	
	@Test
	public void testAddCardWithInValidCard() throws Exception {
		mvc.perform(post("/v1/creditcards/add")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"cardNumber\": \"79927398712\",\"cardHolderName\": \"Test One\",\"cardLimit\": \"5000.00\"}"))
			      .andExpect(status().isBadRequest())
			      .andExpect(content()
			    		  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(content()
			    		  .string(containsString("Invalid Card Number. Please provide a valid value.")));
	}
	
	@Test
	public void testGetAllCardsWithNoData() throws Exception {
		mvc.perform(get("/v1/creditcards/getAll")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			    		  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(content()
			    		  .string(containsString("[]")));
	}
	
	@Test
	@Transactional
	public void testGetAllCardsWithData() throws Exception {
		
		mvc.perform(post("/v1/creditcards/add")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"cardNumber\": \"79927398713\",\"cardHolderName\": \"Test One\",\"cardLimit\": \"5000.00\"}"))
			      .andExpect(status().isCreated());
		
		mvc.perform(post("/v1/creditcards/add")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"cardNumber\": \"889899\",\"cardHolderName\": \"Test One\",\"cardLimit\": \"5000.00\"}"))
			      .andExpect(status().isCreated());
		
		mvc.perform(get("/v1/creditcards/getAll")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			    		  .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(content()
			    		  .string(containsString("[{\"cardNumber\":\"79927398713\",\"cardHolderName\":\"Test One\",\"balance\":0.0,\"cardLimit\":5000.0},{\"cardNumber\":\"889899\",\"cardHolderName\":\"Test One\",\"balance\":0.0,\"cardLimit\":5000.0}]")));
	}

}
