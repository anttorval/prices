package org.example.prices.infrastructure.adapters.input.rest.controllers;

import org.example.prices.application.services.PriceService;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.infrastructure.adapters.input.rest.converters.PriceDTOToGetPriceResponseConverter;
import org.example.prices.infrastructure.adapters.input.rest.responses.GetPriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PriceService priceService;
    @MockBean
    private PriceDTOToGetPriceResponseConverter priceDTOToGetPriceResponseConverter;

    private PriceDTO validPriceDTO;

    @BeforeEach
    void init() {
        validPriceDTO = this.getValidPriceDTO();
    }

    private PriceDTO getValidPriceDTO() {
        return PriceDTO.builder()
                .priceList(1)
                .productId(1L)
                .brandId(1L)
                .startDate(LocalDateTime.of(2024, 04, 12, 22, 12, 05))
                .endDate(LocalDateTime.of(2024, 04, 13, 22, 12, 05))
                .fee(new BigDecimal("100.50"))
                .currency(CurrencyEnum.USD)
                .build();
    }

    @Test
    void givenValidDateProductIdAnBrandId_whenGetPrice_thenSucceed() throws Exception {
        // Given
        GetPriceResponse getPriceResponse = GetPriceResponse.builder()
                .priceList(1)
                .productId(1L)
                .brandId(1L)
                .startDate("12/04/2024 22:12:05")
                .endDate("13/04/2024 22:12:05")
                .fee(new BigDecimal("100.55"))
                .currency(CurrencyEnum.USD)
                .build();

        when(priceService.getPrice(any(), any(), any())).thenReturn(validPriceDTO);
        when(priceDTOToGetPriceResponseConverter.convert(any())).thenReturn(getPriceResponse);

        // Then
        mockMvc.perform(get("/v1/prices")
                .param("date", "31/12/2020 23:59:59")
                .param("product-id", String.valueOf(35455))
                .param("brand-id", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productId").value(getPriceResponse.getProductId()))
                .andExpect(jsonPath("$.brandId").exists())
                .andExpect(jsonPath("$.brandId").value(getPriceResponse.getBrandId()))
                .andExpect(jsonPath("$.priceList").exists())
                .andExpect(jsonPath("$.priceList").value(getPriceResponse.getPriceList()))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.startDate").value(getPriceResponse.getStartDate()))
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.endDate").value(getPriceResponse.getEndDate()))
                .andExpect(jsonPath("$.fee").exists())
                .andExpect(jsonPath("$.fee").value(getPriceResponse.getFee()))
                .andExpect(jsonPath("$.currency").exists())
                .andExpect(jsonPath("$.currency").value(getPriceResponse.getCurrency().name()))
                .andDo(print());
    }

    @Test
    void givenInvalidDate_whenGetPrice_thenErrorResponse() throws Exception {
        // Given
        when(priceService.getPrice(any(), any(), any())).thenReturn(validPriceDTO);

        // Then
        mockMvc.perform(get("/v1/prices")
                .param("date", "31/12/20 23:59:59")
                .param("product-id", String.valueOf(35455))
                .param("brand-id", String.valueOf(1)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Errors"))
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors").isNotEmpty())
                .andExpect(jsonPath("$.errors[0]").value("Invalid date format. The format must be" +
                        " dd/MM/yyyy HH:mm:ss. For example '14/06/2020 15:00:00'"))
                .andDo(print());
    }

    @Test
    void givenInvalidProductId_whenGetPrice_thenErrorResponse() throws Exception {
        // Given
        when(priceService.getPrice(any(), any(), any())).thenReturn(validPriceDTO);

        // Then
        mockMvc.perform(get("/v1/prices")
                .param("date", "31/12/2020 23:59:59")
                .param("product-id", "a")
                .param("brand-id", String.valueOf(1)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Type of request parameter: " +
                        "'product-id' invalid. Failed to convert value of type 'java.lang.String' to required type" +
                        " 'java.lang.Long'; For input string: \"a\""))
                .andDo(print());
    }

    @Test
    void givenInvalidBrandId_whenGetPrice_thenErrorResponse() throws Exception {
        // Given
        when(priceService.getPrice(any(), any(), any())).thenReturn(validPriceDTO);

        // Then
        mockMvc.perform(get("/v1/prices")
                .param("date", "31/12/2020 23:59:59")
                .param("product-id", String.valueOf(35455))
                .param("brand-id", "a"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Type of request parameter: " +
                        "'brand-id' invalid. Failed to convert value of type 'java.lang.String' to required type" +
                        " 'java.lang.Long'; For input string: \"a\""))
                .andDo(print());
    }
}
