package org.example.prices.application.adapters;

import org.example.prices.application.exceptions.ValidationException;
import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.domain.models.Price;
import org.example.prices.domain.ports.output.GetPricePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetPriceUseCaseAdapterTest {

    @InjectMocks
    private GetPriceUseCaseAdapter GetPriceUseCaseAdapter;

    @Mock
    private GetPricePort getPricePort;

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
    void givenValidDateAndProductIdAndBrandId_whenGetPrice_thenSucceed() {
        // Given
        when(getPricePort.getPrice(any(), any(), any())).thenReturn(validPrice);

        // When
        Price result = GetPriceUseCaseAdapter.getPrice(
                LocalDateTime.of(2024, 04, 12, 22, 12, 05),
                1L,
                1L
        );

        // Then
        verify(getPricePort, times(1)).getPrice(
                LocalDateTime.of(2024, 04, 12, 22, 12, 05),
                1L,
                1L
        );

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
    void givenNullDate_whenGetPrice_thenValidationException() {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> GetPriceUseCaseAdapter.getPrice(null, 1L, 1L));

        assertEquals("Date, productId, and brandId cannot be null", exception.getMessage());
    }

    @Test
    void givenNullProductId_whenGetPrice_thenValidationException() {
        LocalDateTime date = LocalDateTime.of(2024, 04, 12, 22, 12, 05);

        ValidationException exception =
                assertThrows(ValidationException.class, () -> GetPriceUseCaseAdapter.getPrice(date, null, 1L));

        assertEquals("Date, productId, and brandId cannot be null", exception.getMessage());
    }

    @Test
    void givenNullBrandId_whenGetPrice_thenValidationException() {
        LocalDateTime date = LocalDateTime.of(2024, 04, 12, 22, 12, 05);

        ValidationException exception =
                assertThrows(ValidationException.class, () -> GetPriceUseCaseAdapter.getPrice(date, 1L, null));

        assertEquals("Date, productId, and brandId cannot be null", exception.getMessage());
    }
}
