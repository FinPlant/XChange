package org.knowm.xchange.bx;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bx.service.BxTradeServiceRaw;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class BxIntegrationTests {

    @Test
    public void getTickerTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName());
        MarketDataService marketDataService = exchange.getMarketDataService();
        Ticker ticker = marketDataService.getTicker(new CurrencyPair("THB", "BTC"));
        System.out.println(ticker.toString());
        assertThat(this).isNotNull();
    }

    @Test
    public void getExchangeSymbolsTest() {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName());
        System.out.println(Arrays.toString(exchange.getExchangeSymbols().toArray()));
    }

    @Test
    public void placeLimitOrderTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        LimitOrder limitOrder = new LimitOrder(
                Order.OrderType.BID,
                new BigDecimal(10),
                new CurrencyPair("THB", "BTC"),
                null,
                null,
                new BigDecimal(20000));
        String orderId = exchange.getTradeService().placeLimitOrder(limitOrder);
        System.out.println(orderId);
    }

    @Test
    public void cancelOrderTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        boolean result = exchange.getTradeService().cancelOrder("8177407");
        System.out.println(result);
    }

    @Test
    public void getOpenOrdersTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        OpenOrders openOrders = exchange.getTradeService().getOpenOrders();
        System.out.println(openOrders.toString());
        assertThat(openOrders).isNotNull();
    }

    @Test
    public void getOrderTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        Collection<Order> orders = exchange.getTradeService().getOrder("8176973", "8177040");
        System.out.println(orders.toString());
        assertThat(orders).isNotNull();
    }

    @Test
    public void getTradeHistoryTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        BxTradeServiceRaw tradeServiceRaw = (BxTradeServiceRaw) exchange.getTradeService();
        tradeServiceRaw.getBxTradeHistory();
    }

    @Test
    public void getBalanceTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        Balance balance = exchange.getAccountService().getAccountInfo().getWallet().getBalance(Currency.THB);
        System.out.println(balance.toString());
        assertThat(balance).isNotNull();
    }

}
