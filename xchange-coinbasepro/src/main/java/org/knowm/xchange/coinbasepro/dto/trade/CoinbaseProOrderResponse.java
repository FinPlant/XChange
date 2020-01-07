package org.knowm.xchange.coinbasepro.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CoinbaseProOrderResponse {
    private final String id;
    private final BigDecimal price;
    private final BigDecimal size;
    private final String productId;
    private final String side;
    private final String stp;
    private final String type;
    private final String timeInForce;
    private final boolean postOnly;
    private final String createdAt;
    private final BigDecimal fillFees;
    private final BigDecimal fillSize;
    private final BigDecimal executedValue;
    private final String status;
    private final boolean settled;

    public CoinbaseProOrderResponse(@JsonProperty("id") String id,
                                    @JsonProperty("price") BigDecimal price,
                                    @JsonProperty("size") BigDecimal size,
                                    @JsonProperty("product_id") String productId,
                                    @JsonProperty("side") String side,
                                    @JsonProperty("stp") String stp,
                                    @JsonProperty("type") String type,
                                    @JsonProperty("time_in_force") String timeInForce,
                                    @JsonProperty("post_only") boolean postOnly,
                                    @JsonProperty("created_at") String createdAt,
                                    @JsonProperty("fill_fees") BigDecimal fillFees,
                                    @JsonProperty("filled_size") BigDecimal fillSize,
                                    @JsonProperty("executed_value") BigDecimal executedValue,
                                    @JsonProperty("status") String status,
                                    @JsonProperty("settled") boolean settled) {
        this.id = id;
        this.price = price;
        this.size = size;
        this.productId = productId;
        this.side = side;
        this.stp = stp;
        this.type = type;
        this.timeInForce = timeInForce;
        this.postOnly = postOnly;
        this.createdAt = createdAt;
        this.fillFees = fillFees;
        this.fillSize = fillSize;
        this.executedValue = executedValue;
        this.status = status;
        this.settled = settled;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getSize() {
        return size;
    }

    public String getProductId() {
        return productId;
    }

    public String getSide() {
        return side;
    }

    public String getStp() {
        return stp;
    }

    public String getType() {
        return type;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getFillFees() {
        return fillFees;
    }

    public BigDecimal getFillSize() {
        return fillSize;
    }

    public BigDecimal getExecutedValue() {
        return executedValue;
    }

    public String getStatus() {
        return status;
    }

    public boolean isSettled() {
        return settled;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CoinbaseExIdResponse [id=");
        builder.append(id);
        builder.append("; price=");
        builder.append(price);
        builder.append("; size=");
        builder.append(size);
        builder.append("; productId=");
        builder.append(productId);
        builder.append("; side=");
        builder.append(side);
        builder.append("; stp=");
        builder.append(stp);
        builder.append("; type=");
        builder.append(type);
        builder.append("; timeInForce=");
        builder.append(timeInForce);
        builder.append("; postOnly=");
        builder.append(postOnly);
        builder.append("; createdAt=");
        builder.append(createdAt);
        builder.append("; fillFees=");
        builder.append(fillFees);
        builder.append("; fillSize=");
        builder.append(fillSize);
        builder.append("; executedValue=");
        builder.append(executedValue);
        builder.append("; status=");
        builder.append(status);
        builder.append("; settled=");
        builder.append(settled);
        builder.append("]");
        return builder.toString();
    }
}
