package org.example.prices.application.services;

import org.example.prices.application.services.dtos.PriceDTO;

public interface PriceService {

    PriceDTO getPrice(String date, Long productId, Long brandId);
}
