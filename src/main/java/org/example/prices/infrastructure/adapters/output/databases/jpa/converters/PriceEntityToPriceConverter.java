package org.example.prices.infrastructure.adapters.output.databases.jpa.converters;

import org.example.prices.domain.models.Price;
import org.example.prices.infrastructure.adapters.output.databases.jpa.entities.PriceEntity;

public interface PriceEntityToPriceConverter {

    Price convert(PriceEntity priceEntity);
}
