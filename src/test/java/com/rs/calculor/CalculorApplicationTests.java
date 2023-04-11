package com.rs.calculor;

import com.rs.calculor.controller.TaxCalculatorController;
import com.rs.calculor.controller.TaxCalculatorDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxCalculatorController.class)
class CalculorApplicationTests {


	private MockMvc mockMvc;
	private TaxCalculatorDTO taxCalculatorDTO;

	@Autowired
	public CalculorApplicationTests(MockMvc mockMvc, TaxCalculatorDTO taxCalculatorDTO) {
		this.mockMvc = mockMvc;
		this.taxCalculatorDTO = taxCalculatorDTO;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testCal () throws Exception {

		taxCalculatorDTO.setTaxPercentage(BigDecimal.valueOf(13.0));
		taxCalculatorDTO.setGrossTaxAmount(BigDecimal.valueOf(500.0));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/calculator").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\"}";


		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);


	}

}
