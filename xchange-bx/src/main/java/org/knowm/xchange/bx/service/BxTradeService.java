package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

import java.io.IOException;
import java.util.Collection;

public class BxTradeService extends BxTradeServiceRaw implements TradeService {

    public BxTradeService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public OpenOrders getOpenOrders() throws IOException {
        return null;
    }

    @Override
    public OpenOrders getOpenOrders(OpenOrdersParams openOrdersParams) throws IOException {
        return null;
    }

    @Override
    public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
        return null;
    }

    @Override
    public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
        return null;
    }

    @Override
    public String placeStopOrder(StopOrder stopOrder) throws IOException {
        return null;
    }

    @Override
    public boolean cancelOrder(String s) throws IOException {
        return false;
    }

    @Override
    public boolean cancelOrder(CancelOrderParams cancelOrderParams) throws IOException {
        return false;
    }

    @Override
    public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
        return null;
    }

    @Override
    public TradeHistoryParams createTradeHistoryParams() {
        return null;
    }

    @Override
    public OpenOrdersParams createOpenOrdersParams() {
        return null;
    }

    @Override
    public Collection<Order> getOrder(String... strings) throws IOException {
        return null;
    }

}
