package org.example.prices.infrastructure.adapters.output.databases.jpa.repositories;

import jakarta.persistence.EntityNotFoundException;
import org.example.prices.domain.enums.CurrencyEnum;
import org.example.prices.domain.models.Price;
import org.example.prices.infrastructure.adapters.output.databases.jpa.converters.PriceEntityToPriceConverter;
import org.example.prices.infrastructure.adapters.output.databases.jpa.entities.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPriceAdapterTest {

    @InjectMocks
    private GetPriceAdapter getPriceAdapter;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    private PriceEntityToPriceConverter priceEntityToPriceConverter;

    private PriceEntity validPriceEntity;
    private Price validPrice;

    @BeforeEach
    void init() {
        validPriceEntity = this.getValidPriceEntity();
        validPrice = this.getValidPrice();
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

    private Price getValidPrice() {
        return Price.builder()
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
    void givenValidDateAndProductIdAndBrandId_whenGetPrice_thenSucceed() {
        //Given
        when(priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                any(), any(),any(),any()))
                .thenReturn(Optional.of(validPriceEntity));
        when(priceEntityToPriceConverter.convert(any())).thenReturn(validPrice);

        // When
        Price result = getPriceAdapter.getPrice(
                LocalDateTime.of(2024, 04, 12, 22, 12, 05),
                1L,
                1L
        );

        // Then
        assertNotNull(result);

        assertEquals(validPriceEntity.getId(), result.getId());
        assertEquals(validPriceEntity.getBrandId(), result.getBrandId());
        assertEquals(validPriceEntity.getProductId(), result.getProductId());
        assertEquals(validPriceEntity.getStartDate(), result.getStartDate());
        assertEquals(validPriceEntity.getEndDate(), result.getEndDate());
        assertEquals(validPriceEntity.getPriceList(), result.getPriceList());
        assertEquals(validPriceEntity.getFee(), result.getFee());
        assertEquals(validPriceEntity.getCurrency(), result.getCurrency());
    }

    @Test
    void givenOptionalEmptyPriceEntity_whenGetPrice_thenEntityNotFoundException() {
        //Given
        when(priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                any(), any(),any(),any()))
                .thenReturn(Optional.empty());

        LocalDateTime date = LocalDateTime.of(2024, 04, 12, 22, 12, 05);

        //When
        //Then
        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> getPriceAdapter.getPrice(date, null, 1L));

        assertEquals("Price not found", exception.getMessage());
    }
}
