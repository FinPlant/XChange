package org.knowm.xchange.coinbasepro.dto.trade;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.knowm.xchange.coinbasepro.CoinbaseProAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import si.mazi.rescu.serialization.jackson.DefaultJacksonObjectMapperFactory;
import si.mazi.rescu.serialization.jackson.JacksonObjectMapperFactory;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinbaseProPlaceOrderTest {

    @Test
    public void nullflagsTest() {
        LimitOrder limitOrder =
                new LimitOrder.Builder(OrderType.BID, CurrencyPair.BTC_USD)
                        .limitPrice(BigDecimal.ZERO)
                        .build();

        CoinbaseProPlaceLimitOrder orderFlagsNull =
                CoinbaseProAdapters.adaptCoinbaseProPlaceLimitOrder(limitOrder);
        assertThat(orderFlagsNull.getPostOnly()).isEqualTo(null);
        assertThat(orderFlagsNull.getTimeInForce()).isEqualTo(null);
    }

    @Test
    public void fillOrKillflagTest() {
        LimitOrder order =
                new LimitOrder.Builder(OrderType.BID, CurrencyPair.BTC_USD)
                        .limitPrice(BigDecimal.ZERO)
                        .flag(CoinbaseProOrderFlags.FILL_OR_KILL)
                        .build();

        CoinbaseProPlaceLimitOrder orderFOK =
                CoinbaseProAdapters.adaptCoinbaseProPlaceLimitOrder(order);

        assertThat(orderFOK.getPostOnly()).isEqualTo(null);
        assertThat(orderFOK.getTimeInForce()).isEqualTo(CoinbaseProPlaceLimitOrder.TimeInForce.FOK);
    }

    @Test
    public void postOnlyflagTest() {
        LimitOrder order =
                new LimitOrder.Builder(OrderType.BID, CurrencyPair.BTC_USD)
                        .limitPrice(BigDecimal.ZERO)
                        .flag(CoinbaseProOrderFlags.POST_ONLY)
                        .build();

        CoinbaseProPlaceLimitOrder orderPostOnly =
                CoinbaseProAdapters.adaptCoinbaseProPlaceLimitOrder(order);

        assertThat(orderPostOnly.getPostOnly()).isEqualTo(Boolean.TRUE);
        assertThat(orderPostOnly.getTimeInForce()).isEqualTo(null);
    }

    @Test
    public void immediateOrCancelflagTest() {
        LimitOrder order =
                new LimitOrder.Builder(OrderType.BID, CurrencyPair.BTC_USD)
                        .limitPrice(BigDecimal.ZERO)
                        .flag(CoinbaseProOrderFlags.IMMEDIATE_OR_CANCEL)
                        .build();

        CoinbaseProPlaceLimitOrder orderIOC =
                CoinbaseProAdapters.adaptCoinbaseProPlaceLimitOrder(order);

        assertThat(orderIOC.getPostOnly()).isEqualTo(null);
        assertThat(orderIOC.getTimeInForce()).isEqualTo(CoinbaseProPlaceLimitOrder.TimeInForce.IOC);
    }

    @Test
    public void orderResponseTest() throws IOException {
        JacksonObjectMapperFactory factory = new DefaultJacksonObjectMapperFactory();
        ObjectMapper mapper = factory.createObjectMapper();

        InputStream is =
                getClass()
                        .getResourceAsStream("/org/knowm/xchange/coinbasepro/dto/order/example-market-order-response.json");
        CoinbaseProOrderResponse response = mapper.readValue(is, CoinbaseProOrderResponse.class);

        assertThat(response.getId()).isEqualTo("d0c5340b-6d6c-49d9-b567-48c4bfca13d2");
        assertThat(response.getPrice()).isEqualTo("0.10000000");
        assertThat(response.getSize()).isEqualTo("0.01000000");
        assertThat(response.getProductId()).isEqualTo("BTC-USD");
        assertThat(response.getSide()).isEqualTo("buy");
        assertThat(response.getStp()).isEqualTo("dc");
        assertThat(response.getType()).isEqualTo("limit");
        assertThat(response.getTimeInForce()).isEqualTo("GTC");
        assertThat(response.isPostOnly()).isEqualTo(false);
        assertThat(response.getCreatedAt()).isEqualTo("2016-12-08T20:02:28.53864Z");
        assertThat(response.getFillFees()).isEqualTo("0.0000000000000000");
        assertThat(response.getFillSize()).isEqualTo("0.00000000");
        assertThat(response.getExecutedValue()).isEqualTo("0.0000000000000000");
        assertThat(response.getStatus()).isEqualTo("pending");
        assertThat(response.isSettled()).isEqualTo(false);
    }
}
