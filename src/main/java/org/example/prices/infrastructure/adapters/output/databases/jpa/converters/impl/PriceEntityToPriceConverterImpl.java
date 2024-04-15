package org.example.prices.infrastructure.adapters.output.databases.jpa.converters.impl;


import org.example.prices.domain.models.Price;
import org.example.prices.infrastructure.adapters.output.databases.jpa.converters.PriceEntityToPriceConverter;
import org.example.prices.infrastructure.adapters.output.databases.jpa.entities.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public final class PriceEntityToPriceConverterImpl implements PriceEntityToPriceConverter {

    @Override
    public Price convert(PriceEntity priceEntity) {
        if (priceEntity == null) {
            return null;
        }
        return Price.builder()
                .id(priceEntity.getId())
                .brandId(priceEntity.getBrandId())
                .productId(priceEntity.getProductId())
                .priority(priceEntity.getPriority())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .priceList(priceEntity.getPriceList())
                .fee(priceEntity.getFee())
                .currency(priceEntity.getCurrency())
                .build();
    }
}
