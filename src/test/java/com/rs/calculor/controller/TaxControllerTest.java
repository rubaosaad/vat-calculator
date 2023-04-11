package com.rs.calculor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rs.calculor.service.TaxCalculatorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaxCalculatorController.class)
public class TaxControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    private TaxCalculatorDTO taxCalculatorDTO;

    @MockBean
    private TaxCalculatorService taxCalculatorService;
    private String URI = "/calculator";

    @Test
    void taxAmountTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(BigDecimal.valueOf(13.0), null, BigDecimal.valueOf(54.17), null);

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        TaxCalculatorDTO service = new TaxCalculatorService().calculate(taxCalculatorDTO);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReturn = ow.writeValueAsString(service);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReturn);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals("{\"taxPercentage\":13.0,\"grossTaxAmount\":416.69,\"taxAmount\":54.17,\"totalAmount\":470.86}",
                result.getResponse().getContentAsString(),
                false);

    }

    //@Test
    void grossTaxAmountTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(BigDecimal.valueOf(13.0), BigDecimal.valueOf(416.69), null, null);

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        TaxCalculatorDTO service = new TaxCalculatorService().calculate(taxCalculatorDTO);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReturn = ow.writeValueAsString(service);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReturn);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals("{\"taxPercentage\":13.0,\"grossTaxAmount\":416.69,\"taxAmount\":54.17,\"totalAmount\":470.86}",
                result.getResponse().getContentAsString(),
                false);
    }

    //@Test
    void totalAmountTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(BigDecimal.valueOf(13.0), null, null, BigDecimal.valueOf(470.86));

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        TaxCalculatorDTO service = new TaxCalculatorService().calculate(taxCalculatorDTO);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReturn = ow.writeValueAsString(service);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReturn);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals("{\"taxPercentage\":13.0,\"grossTaxAmount\":416.69,\"taxAmount\":54.17,\"totalAmount\":470.86}",
                result.getResponse().getContentAsString(),
                false);
    }

    //@Test
    void moreThanOneTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(BigDecimal.valueOf(13.0), null, BigDecimal.valueOf(54.17), BigDecimal.valueOf(470.86));

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andReturn();

        Assertions.assertThat(result).isNotNull();
        String userJson = result.getResponse().getContentAsString();
        Assertions.assertThat(userJson).isEqualToIgnoringCase(mapper.writeValueAsString(taxCalculatorDTO));
    }

    //@Test
    void withoutTaxPercentageTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(null, null, BigDecimal.valueOf(54.17), BigDecimal.valueOf(470.86));

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        TaxCalculatorDTO service = new TaxCalculatorService().calculate(taxCalculatorDTO);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReturn = ow.writeValueAsString(service);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReturn);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals("{\"taxPercentage\":13.0,\"grossTaxAmount\":416.69,\"taxAmount\":54.17,\"totalAmount\":470.86}",
                result.getResponse().getContentAsString(),
                false);
    }

    //@Test
    void withoutParametersTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(BigDecimal.valueOf(13.0), null, null, null);

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        TaxCalculatorDTO service = new TaxCalculatorService().calculate(taxCalculatorDTO);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReturn = ow.writeValueAsString(service);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReturn);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals("{\"taxPercentage\":13.0,\"grossTaxAmount\":416.69,\"taxAmount\":54.17,\"totalAmount\":470.86}",
                result.getResponse().getContentAsString(),
                false);
    }


    //@Test
    void differentTaxPercentageTest() throws Exception {

        taxCalculatorDTO = new TaxCalculatorDTO(BigDecimal.valueOf(77), null, null, null);

        Mockito.when(taxCalculatorService.calculate(taxCalculatorDTO)).thenReturn(taxCalculatorDTO);

        TaxCalculatorDTO service = new TaxCalculatorService().calculate(taxCalculatorDTO);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonReturn = ow.writeValueAsString(service);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonReturn);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals("{\"taxPercentage\":13.0,\"grossTaxAmount\":416.69,\"taxAmount\":54.17,\"totalAmount\":470.86}",
                result.getResponse().getContentAsString(),
                false);
    }

}
