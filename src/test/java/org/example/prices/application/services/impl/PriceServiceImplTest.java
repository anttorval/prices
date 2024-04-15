package org.example.prices.application.services.impl;

import org.example.prices.application.converters.DateConverter;
import org.example.prices.application.converters.PriceToPriceDTOConverter;
import org.example.prices.application.exceptions.ValidationException;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.domain.models.Price;
import org.example.prices.domain.ports.input.GetPriceUseCasePort;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl priceServiceImpl;

    @Mock
    private GetPriceUseCasePort getPriceUseCasePort;
    @Mock
    private PriceToPriceDTOConverter priceToPriceDTOConverter;
    @Mock
    private DateConverter dateConverter;

    private Price validPrice;
    private PriceDTO validPriceDTO;

    @BeforeEach
    void init() {
        validPrice = this.getValidPrice();
        validPriceDTO = this.getValidPriceDTO();
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
    void givenValidDateAndProductIdAndBrandId_whenGetPrice_thenSucceed() {
        // Given
        ReflectionTestUtils.setField(priceServiceImpl, "dateRegex", "dd/MM/yyyy HH:mm:ss");
        when(getPriceUseCasePort.getPrice(any(), any(), any())).thenReturn(validPrice);
        when(priceToPriceDTOConverter.convert(any())).thenReturn(validPriceDTO);
        when(dateConverter.convert(anyString(), any())).thenReturn(
                LocalDateTime.of(2024, 04, 12, 22, 12, 05)
        );

        // When
        PriceDTO result = priceServiceImpl.getPrice("12/04/2024 22:12:05", 1L, 1L);

        // Then
        verify(getPriceUseCasePort, times(1)).getPrice(
                LocalDateTime.of(2024, 04, 12, 22, 12, 05),
                1L,
                1L
        );
        verify(priceToPriceDTOConverter, times(1)).convert(validPrice);
        verify(dateConverter, times(1)).convert("12/04/2024 22:12:05", "dd/MM/yyyy HH:mm:ss");

        assertNotNull(result);

        assertEquals(validPriceDTO.getBrandId(), result.getBrandId());
        assertEquals(validPriceDTO.getProductId(), result.getProductId());
        assertEquals(validPriceDTO.getStartDate(), result.getStartDate());
        assertEquals(validPriceDTO.getEndDate(), result.getEndDate());
        assertEquals(validPriceDTO.getPriceList(), result.getPriceList());
        assertEquals(validPriceDTO.getFee(), result.getFee());
        assertEquals(validPriceDTO.getCurrency(), result.getCurrency());
    }

    @Test
    void givenNullDate_whenGetPrice_thenValidationException() {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> priceServiceImpl.getPrice(
                        null,
                        1L,
                        1L)
                );

        assertEquals("Date, productId, and brandId cannot be null", exception.getMessage());
    }

    @Test
    void givenNullProductId_whenGetPrice_thenValidationException() {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> priceServiceImpl.getPrice(
                        "12/04/2024 22:12:05",
                        null,
                        1L)
                );

        assertEquals("Date, productId, and brandId cannot be null", exception.getMessage());
    }

    @Test
    void givenNullBrandId_whenGetPrice_thenValidationException() {
        ValidationException exception =
                assertThrows(ValidationException.class, () -> priceServiceImpl.getPrice(
                        "12/04/2024 22:12:05",
                        1L,
                        null)
                );

        assertEquals("Date, productId, and brandId cannot be null", exception.getMessage());
    }
}
