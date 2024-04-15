package org.example.prices.infrastructure.adapters.input.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.example.prices.application.services.PriceService;
import org.example.prices.application.services.dtos.PriceDTO;
import org.example.prices.infrastructure.adapters.input.rest.converters.PriceDTOToGetPriceResponseConverter;
import org.example.prices.infrastructure.adapters.input.rest.responses.GetPriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/prices")
@RequiredArgsConstructor
@Validated
@Tag(name = "v1/prices")
public class PriceController {

    private final PriceService priceService;
    private final PriceDTOToGetPriceResponseConverter priceDTOToGetPriceResponseConverter;

    @GetMapping
    @Operation(
            method = "GET",
            description = "Return the price filtering by date, product and brand"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "201", description = "Price created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Price not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetPriceResponse> getPrice(
            @Parameter(example = "14/06/2020 15:30:00", required = true, description = "Date on which the price applies. " +
                    "If there are several prices, the one with the highest priority is returned. Pattern: dd/MM/yyyy HH:mm:ss")
            @Pattern(regexp = "^((0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4} (?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d)$",
                message = "{date.format.invalid}")
            @RequestParam("date") String date,
            @Parameter(example = "35455", required = true, description = "Product ID")
            @RequestParam("product-id") Long productId,
            @Parameter(example = "1", required = true, description = "Brand ID")
            @RequestParam("brand-id") Long brandId){

        PriceDTO priceDTO = this.priceService.getPrice(date, productId, brandId);

        GetPriceResponse getPriceResponse = this.priceDTOToGetPriceResponseConverter.convert(priceDTO);

        return ResponseEntity.ok(getPriceResponse);
    }
}
