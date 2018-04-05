package org.knowm.xchange.bx;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bx.service.BxTradeHistoryParams;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BxIntegrationTests {

    @Test
    public void getTickerTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName());
        MarketDataService marketDataService = exchange.getMarketDataService();
        Ticker ticker = marketDataService.getTicker(new CurrencyPair("THB", "BTC"));
        System.out.println(ticker.toString());
        assertThat(ticker).isNotNull();
    }

    @Test
    public void getExchangeSymbolsTest() {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName());
        List<CurrencyPair> pairs = exchange.getExchangeSymbols();
        System.out.println(Arrays.toString(pairs.toArray()));
        assertThat(pairs).isNotNull();
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
        assertThat(orderId).isNotBlank();
    }

    @Test
    public void cancelOrderTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        boolean result = exchange.getTradeService().cancelOrder("8200823");
        System.out.println(result);
        assertThat(result).isTrue();
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
        Collection<Order> orders = exchange.getTradeService().getOrder("8200823", "8177040");
        System.out.println(orders.toString());
        assertThat(orders).isNotNull();
    }

    @Test
    public void getTradeHistoryMarTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        TradeService tradeService = exchange.getTradeService();
        BxTradeHistoryParams params = new BxTradeHistoryParams("2018-03-01 00:00:00", "2018-03-31 23:59:59");
        UserTrades trades = tradeService.getTradeHistory(params);
        System.out.println(trades);
        assertThat(trades).isNotNull();
    }

    @Test
    public void getTradeHistoryTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        TradeService tradeService = exchange.getTradeService();
        UserTrades trades = tradeService.getTradeHistory(null);
        System.out.println(trades);
        assertThat(trades).isNotNull();
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
