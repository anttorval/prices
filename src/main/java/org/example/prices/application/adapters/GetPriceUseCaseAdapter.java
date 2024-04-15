package org.example.prices.application.adapters;

import lombok.RequiredArgsConstructor;
import org.example.prices.application.exceptions.ValidationException;
import org.example.prices.domain.models.Price;
import org.example.prices.domain.ports.input.GetPriceUseCasePort;
import org.example.prices.domain.ports.output.GetPricePort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetPriceUseCaseAdapter implements GetPriceUseCasePort {

    private final GetPricePort getPricePort;

    @Override
    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        if (Objects.isNull(date) || Objects.isNull(productId) || Objects.isNull(brandId)) {
            throw new ValidationException("Date, productId, and brandId cannot be null");
        }

        return getPricePort.getPrice(date, productId, brandId);
    }
}
