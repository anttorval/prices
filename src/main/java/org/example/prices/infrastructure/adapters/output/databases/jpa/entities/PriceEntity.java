package org.example.prices.infrastructure.adapters.output.databases.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.prices.domain.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "prices", indexes = {@Index(columnList = "start_date, end_date, product_id, brand_id, priority")})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "price_list")
    private Integer priceList;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "price")
    private BigDecimal fee;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

}
