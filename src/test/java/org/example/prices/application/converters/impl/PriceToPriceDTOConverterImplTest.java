package org.example.prices.application.converters.impl;

import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceToPriceDTOConverterImplTest {

    @InjectMocks
    private PriceToPriceDTOConverterImpl priceToPriceDTOConverterImpl;

    private Price validPrice;

    @BeforeEach
    void init() {
        validPrice = this.getValidPrice();
    }

    private Price getValidPrice() {
        return Price.builder()
                .id(1L)
                .priceList(1)
                .priority(1)
                .productId(1L)
                .brandId(1L)
                .startDate(LocalDateTime.of(2024, 04, 12, 22, 12, 05))
                .endDate(LocalDateTime.of(2024, 04, 13, 22, 12, 05))
                .fee(new BigDecimal("100.50"))
                .currency(CurrencyEnum.USD)
                .build();
    }

    @Test
    void givenValidPrice_whenConvert_thenSucceed() {
        // When
        PriceDTO result = priceToPriceDTOConverterImpl.convert(validPrice);

        // Then
        assertNotNull(result);

        assertEquals(validPrice.getBrandId(), result.getBrandId());
        assertEquals(validPrice.getProductId(), result.getProductId());
        assertEquals(validPrice.getStartDate(), result.getStartDate());
        assertEquals(validPrice.getEndDate(), result.getEndDate());
        assertEquals(validPrice.getPriceList(), result.getPriceList());
        assertEquals(validPrice.getFee(), result.getFee());
        assertEquals(validPrice.getCurrency(), result.getCurrency());
    }

    @Test
    void givenNullPrice_whenConvert_thenReturnNull() {
        // When
        PriceDTO result = priceToPriceDTOConverterImpl.convert(null);

        // Then
        assertNull(result);
    }
}
