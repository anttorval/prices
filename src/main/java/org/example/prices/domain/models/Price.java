package org.example.prices.domain.models;

import org.example.prices.domain.enums.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Price {

    private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal fee;
    private CurrencyEnum currency;

    private Price(Builder builder) {
        setId(builder.id);
        setBrandId(builder.brandId);
        setStartDate(builder.startDate);
        setEndDate(builder.endDate);
        setPriceList(builder.priceList);
        setProductId(builder.productId);
        setPriority(builder.priority);
        setFee(builder.fee);
        setCurrency(builder.currency);
    }

    public Long getId() {
        return id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getPriceList() {
        return priceList;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getPriority() {
        return priority;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Objects.equals(id, price1.id) && Objects.equals(brandId, price1.brandId)
                && Objects.equals(startDate, price1.startDate) && Objects.equals(endDate, price1.endDate)
                && Objects.equals(priceList, price1.priceList) && Objects.equals(productId, price1.productId)
                && Objects.equals(priority, price1.priority) && Objects.equals(fee, price1.fee)
                && currency == price1.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandId, startDate, endDate, priceList, productId, priority, fee, currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceList=" + priceList +
                ", productId=" + productId +
                ", priority=" + priority +
                ", fee=" + fee +
                ", currency=" + currency +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private Long brandId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Integer priceList;
        private Long productId;
        private Integer priority;
        private BigDecimal fee;
        private CurrencyEnum currency;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder brandId(Long val) {
            brandId = val;
            return this;
        }

        public Builder startDate(LocalDateTime val) {
            startDate = val;
            return this;
        }

        public Builder endDate(LocalDateTime val) {
            endDate = val;
            return this;
        }

        public Builder priceList(Integer val) {
            priceList = val;
            return this;
        }

        public Builder productId(Long val) {
            productId = val;
            return this;
        }

        public Builder priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder fee(BigDecimal val) {
            fee = val;
            return this;
        }

        public Builder currency(CurrencyEnum val) {
            currency = val;
            return this;
        }

        public Price build() {
            return new Price(this);
        }
    }
}
