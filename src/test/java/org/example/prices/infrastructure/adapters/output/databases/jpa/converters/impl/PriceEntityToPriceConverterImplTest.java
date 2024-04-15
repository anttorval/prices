package org.example.prices.infrastructure.adapters.output.databases.jpa.converters.impl;

import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.domain.models.Price;
import org.example.prices.infrastructure.adapters.output.databases.jpa.entities.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceEntityToPriceConverterImplTest {

    @InjectMocks
    private PriceEntityToPriceConverterImpl priceEntityToPriceConverterImpl;

    private PriceEntity priceEntity;

    @BeforeEach
    void init() {
        priceEntity = this.getValidPriceEntity();
    }

    private PriceEntity getValidPriceEntity() {
        return PriceEntity.builder()
                .id(1L)
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
    void givenValidPriceEntity_whenConvert_thenSucceed() {
        // When
        Price result = priceEntityToPriceConverterImpl.convert(priceEntity);

        // Then
        assertNotNull(result);

        assertEquals(priceEntity.getId(), result.getId());
        assertEquals(priceEntity.getBrandId(), result.getBrandId());
        assertEquals(priceEntity.getProductId(), result.getProductId());
        assertEquals(priceEntity.getStartDate(), result.getStartDate());
        assertEquals(priceEntity.getEndDate(), result.getEndDate());
        assertEquals(priceEntity.getPriceList(), result.getPriceList());
        assertEquals(priceEntity.getFee(), result.getFee());
        assertEquals(priceEntity.getCurrency(), result.getCurrency());
    }

    @Test
    void givenNullPriceEntity_whenConvert_thenReturnNull() {
        // When
        Price result = priceEntityToPriceConverterImpl.convert(null);

        // Then
        assertNull(result);
    }
}
