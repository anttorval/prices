package org.example.prices.domain.ports.input;

import org.example.prices.domain.models.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCasePort {

    Price getPrice(LocalDateTime date, Long productId, Long brandId);
}
