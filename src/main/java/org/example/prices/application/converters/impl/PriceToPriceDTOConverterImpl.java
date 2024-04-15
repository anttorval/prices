package org.example.prices.application.converters.impl;

import org.example.prices.application.converters.PriceToPriceDTOConverter;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.models.Price;
import org.springframework.stereotype.Component;

@Component
public final class PriceToPriceDTOConverterImpl implements PriceToPriceDTOConverter {

    @Override
    public PriceDTO convert(Price price) {
        if (price == null) {
            return null;
        }
        return PriceDTO.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .fee(price.getFee())
                .currency(price.getCurrency())
                .build();
    }
}
