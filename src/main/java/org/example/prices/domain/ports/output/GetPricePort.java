package org.example.prices.domain.ports.output;

import org.example.prices.domain.models.Price;

import java.time.LocalDateTime;

public interface GetPricePort {

    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
