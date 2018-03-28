package org.knowm.xchange.bx;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bx.service.BxAccountServiceRaw;
import org.knowm.xchange.bx.service.BxTradeServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Arrays;

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

    public void placeLimitOrderTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());

    }

    @Test
    public void cancelOrderTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        BxTradeServiceRaw tradeServiceRaw = (BxTradeServiceRaw) exchange.getTradeService();
        tradeServiceRaw.cancelBxOrder("2", "123");
    }

    @Test
    public void getOrdersTest() throws IOException {
        BxProperties properties = new BxProperties();
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BxExchange.class.getName(), properties.getApiKey(),
                properties.getSecretKey());
        BxTradeServiceRaw tradeServiceRaw = (BxTradeServiceRaw) exchange.getTradeService();
        tradeServiceRaw.getBxOrders();
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
        BxAccountServiceRaw accountServiceRaw = (BxAccountServiceRaw) exchange.getAccountService();
        accountServiceRaw.getBxBalance();
    }

}
