package org.example.prices.infrastructure.adapters.input.rest.converters.impl;

import lombok.RequiredArgsConstructor;
import org.example.prices.application.converters.DateConverter;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.infrastructure.adapters.input.rest.converters.PriceDTOToGetPriceResponseConverter;
import org.example.prices.infrastructure.adapters.input.rest.responses.GetPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class PriceDTOToGetPriceResponseConverterImpl implements PriceDTOToGetPriceResponseConverter {

    @Value("${datetime.format.pattern}")
    private String dateTimePattern;

    private final DateConverter dateConverter;

    @Override
    public GetPriceResponse convert(PriceDTO priceDTO) {
        if (priceDTO == null) {
            return null;
        }

        return GetPriceResponse.builder()
                .productId(priceDTO.getProductId())
                .brandId(priceDTO.getBrandId())
                .priceList(priceDTO.getPriceList())
                .startDate(dateConverter.convert(priceDTO.getStartDate(), dateTimePattern))
                .endDate(dateConverter.convert(priceDTO.getEndDate(), dateTimePattern))
                .fee(priceDTO.getFee())
                .currency(priceDTO.getCurrency())
                .build();
    }
}
