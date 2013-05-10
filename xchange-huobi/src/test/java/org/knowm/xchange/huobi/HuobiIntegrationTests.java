package org.knowm.xchange.huobi;

import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.huobi.dto.account.HuobiAccount;
import org.knowm.xchange.huobi.service.HuobiAccountService;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class HuobiIntegrationTests {

    @Test
    public void getTickerTest() throws Exception {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        MarketDataService marketDataService = exchange.getMarketDataService();
        Ticker ticker = marketDataService.getTicker(new CurrencyPair("BTC","USDT"));
        System.out.println(ticker.toString());
        assertThat(ticker).isNotNull();
    }

    @Test
    public void getExchangeSymbolsTest() {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        System.out.println(Arrays.toString(exchange.getExchangeSymbols().toArray()));
    }

    @Test
    public void getAccountTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        HuobiAccountService accountService = (HuobiAccountService) exchange.getAccountService();
        HuobiAccount[] accounts = accountService.getAccounts();
        Arrays.toString(accounts);
    }

    @Test
    public void getBalanceTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        AccountService accountService = exchange.getAccountService();
        Balance balance = accountService.getAccountInfo().getWallet().getBalance(Currency.USDT);
        System.out.println(balance.toString());
        assertThat(balance).isNotNull();
    }

    @Test
    public void getOpenOrdersTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        TradeService tradeService = exchange.getTradeService();
        OpenOrders openOrders = tradeService.getOpenOrders();
        System.out.println(openOrders.toString());
        assertThat(openOrders).isNotNull();
    }

    @Test
    public void cancelOrderTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        TradeService tradeService = exchange.getTradeService();
        boolean result = tradeService.cancelOrder("123");
        System.out.println(result);
    }

    @Test
    public void placeLimitOrderTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        TradeService tradeService = exchange.getTradeService();
        HuobiAccountService accountService = (HuobiAccountService) exchange.getAccountService();
        HuobiAccount[] accounts = accountService.getAccounts();
        LimitOrder limitOrder = new LimitOrder(
                OrderType.BID,
                new BigDecimal("1.01"),
                new CurrencyPair("QTUM", "USDT"),
                String.valueOf(accounts[0].getId()),
                null,
                new BigDecimal("30")
        );
        String orderId = tradeService.placeLimitOrder(limitOrder);
        System.out.println(orderId);
    }

    @Test
    public void placeMarketOrderTest() throws IOException {
        Exchange exchange = ExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName());
        TradeService tradeService = exchange.getTradeService();
        HuobiAccountService accountService = (HuobiAccountService) exchange.getAccountService();
        HuobiAccount[] accounts = accountService.getAccounts();
        MarketOrder marketOrder = new MarketOrder(
                OrderType.BID,
                new BigDecimal("0.001"),
                new CurrencyPair("BTC", "USDT"),
                String.valueOf(accounts[0].getId()),
                null
        );
        String orderId = tradeService.placeMarketOrder(marketOrder);
        System.out.println(orderId);
    }

}
