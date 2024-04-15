package org.example.prices.infrastructure.adapters.input.rest.converters;

import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.infrastructure.adapters.input.rest.responses.GetPriceResponse;

public interface PriceDTOToGetPriceResponseConverter {

    GetPriceResponse convert(PriceDTO priceDTO);
}
