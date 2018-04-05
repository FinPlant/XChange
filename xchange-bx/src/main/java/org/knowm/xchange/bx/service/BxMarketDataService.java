package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bx.BxAdapters;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

public class BxMarketDataService extends BxMarketDataServiceRaw implements MarketDataService {

    public BxMarketDataService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public Ticker getTicker(CurrencyPair currencyPair, Object... objects) throws IOException {
        return BxAdapters.adaptTicker(getBxTicker(currencyPair), exchange.getNonceFactory());
    }

    @Override
    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... objects) throws IOException {
        return null;
    }

    @Override
    public Trades getTrades(CurrencyPair currencyPair, Object... objects) throws IOException {
        return null;
    }

}
