package org.example.prices.infrastructure.adapters.output.databases.jpa.repositories;

import org.example.prices.infrastructure.adapters.output.databases.jpa.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity,Long> {

    Optional<PriceEntity>
    findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId);

}
