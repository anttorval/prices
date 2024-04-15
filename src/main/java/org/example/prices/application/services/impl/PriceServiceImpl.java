package org.example.prices.application.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.prices.application.converters.DateConverter;
import org.example.prices.application.converters.PriceToPriceDTOConverter;
import org.example.prices.application.exceptions.ValidationException;
import org.example.prices.application.services.PriceService;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.domain.models.Price;
import org.example.prices.domain.ports.input.GetPriceUseCasePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    @Value("${datetime.format.pattern}")
    private String dateRegex;

    private final GetPriceUseCasePort getPriceUseCasePort;
    private final PriceToPriceDTOConverter priceToPriceDTOConverter;
    private final DateConverter dateConverter;

    @Override
    public PriceDTO getPrice(String date, Long productId, Long brandId) {
        if (Objects.isNull(date) || Objects.isNull(productId) || Objects.isNull(brandId)) {
            throw new ValidationException("Date, productId, and brandId cannot be null");
        }

        LocalDateTime localDateTime = dateConverter.convert(date, dateRegex);
        Price price = getPriceUseCasePort.getPrice(localDateTime, productId, brandId);
        return priceToPriceDTOConverter.convert(price);
    }
}
