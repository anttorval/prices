package org.example.prices.application.converters;

import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.models.Price;

public interface PriceToPriceDTOConverter {

    PriceDTO convert(Price price);
}
