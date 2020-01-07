package org.knowm.xchange.coinbasepro.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CoinbaseProOrderResponse {
    private final String id;
    private final BigDecimal price;
    private final BigDecimal size;

    public CoinbaseProOrderResponse(@JsonProperty("id") String id,
                                    @JsonProperty("price") BigDecimal price,
                                    @JsonProperty("size") BigDecimal size) {
        this.id = id;
        this.price = price;
        this.size = size;
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



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CoinbaseExIdResponse [id=");
        builder.append(id);
        builder.append("; price=");
        builder.append(price);
        builder.append("; size=");
        builder.append(size);
        builder.append("]");
        return builder.toString();
    }
}
