package org.example.prices.infrastructure.adapters.output.databases.jpa.repositories;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.prices.domain.models.Price;
import org.example.prices.domain.ports.output.GetPricePort;
import org.example.prices.infrastructure.adapters.output.databases.jpa.converters.PriceEntityToPriceConverter;
import org.example.prices.infrastructure.adapters.output.databases.jpa.entities.PriceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetPriceAdapter implements GetPricePort {

    private final PriceRepository priceRepository;
    private final PriceEntityToPriceConverter priceEntityToPriceConverter;

    @Override
    public Price getPrice(LocalDateTime date, Long productId, Long brandId) {
        Optional<PriceEntity> priceEntityOptional = priceRepository.
                findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        date, date, productId, brandId);

        if(priceEntityOptional.isEmpty()){
            throw new EntityNotFoundException("Price not found");
        }

        return priceEntityToPriceConverter.convert(priceEntityOptional.get());
    }
}
