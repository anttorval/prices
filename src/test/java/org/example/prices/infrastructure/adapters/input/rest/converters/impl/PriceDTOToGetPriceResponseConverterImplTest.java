package org.example.prices.infrastructure.adapters.input.rest.converters.impl;

import org.example.prices.application.converters.DateConverter;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.infrastructure.adapters.input.rest.responses.GetPriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceDTOToGetPriceResponseConverterImplTest {

    @InjectMocks
    private PriceDTOToGetPriceResponseConverterImpl priceDTOToGetPriceResponseConverterImpl;

    @Mock
    private DateConverter dateConverter;

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
    void givenValidPriceDTO_whenConvert_thenSucceed() {
        //Given
        ReflectionTestUtils.setField(priceDTOToGetPriceResponseConverterImpl, "dateTimePattern", "dd/MM/yyyy HH:mm:ss");
        when(dateConverter.convert(
                LocalDateTime.of(2024, 04, 12, 22, 12, 05),
                "dd/MM/yyyy HH:mm:ss"))
                .thenReturn("12/04/2024 22:12:05");
        when(dateConverter.convert(
                LocalDateTime.of(2024, 04, 13, 22, 12, 05),
                "dd/MM/yyyy HH:mm:ss"))
                .thenReturn("13/04/2024 22:12:05");

        // When
        GetPriceResponse result = priceDTOToGetPriceResponseConverterImpl.convert(validPriceDTO);

        // Then
        assertNotNull(result);

        assertEquals(validPriceDTO.getBrandId(), result.getBrandId());
        assertEquals(validPriceDTO.getProductId(), result.getProductId());
        assertEquals("12/04/2024 22:12:05", result.getStartDate());
        assertEquals("13/04/2024 22:12:05", result.getEndDate());
        assertEquals(validPriceDTO.getPriceList(), result.getPriceList());
        assertEquals(validPriceDTO.getFee(), result.getFee());
        assertEquals(validPriceDTO.getCurrency(), result.getCurrency());
    }

    @Test
    void givenNullPrice_whenConvert_thenReturnNull() {
        // When
        GetPriceResponse result = priceDTOToGetPriceResponseConverterImpl.convert(null);

        // Then
        assertNull(result);
    }

}
