package org.example.prices.infrastructure.adapters.input.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.example.prices.domain.enums.CurrencyEnum;

import java.math.BigDecimal;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPriceResponse {

    @Schema(description = "Product identificator", example = "1")
    private Long productId;
    @Schema(description = "Brand identificator", example = "1")
    private Long brandId;
    @Schema(description = "Price List identificator", example = "1")
    private Integer priceList;
    @Schema(description = "Start date where the Price is enable", example = "14/06/2020 15:30:00")
    private String startDate;
    @Schema(description = "End date where the Price is enable", example = "15/06/2020 15:30:00")
    private String endDate;
    @Schema(description = "Fee", example = "35.5")
    private BigDecimal fee;
    @Schema(description = "ISO Currency", example = "USD")
    private CurrencyEnum currency;

}
