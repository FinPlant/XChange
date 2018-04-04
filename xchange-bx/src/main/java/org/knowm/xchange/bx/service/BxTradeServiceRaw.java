package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bx.BxAdapters;
import org.knowm.xchange.bx.BxUtils;
import org.knowm.xchange.bx.dto.trade.BxOrder;
import org.knowm.xchange.bx.dto.trade.BxTradeHistory;
import org.knowm.xchange.bx.dto.trade.results.BxCancelOrderResult;
import org.knowm.xchange.bx.dto.trade.results.BxCreateOrderResult;
import org.knowm.xchange.bx.dto.trade.results.BxOrdersResult;
import org.knowm.xchange.bx.dto.trade.results.BxTradeHistoryResult;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class BxTradeServiceRaw extends BxBaseService {

    private static final String TRADE = "trade";

    BxTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    boolean cancelBxOrder(String orderId) throws IOException {
        BxCancelOrderResult result = bx.cancelOrder(null, orderId, exchange.getExchangeSpecification().getApiKey(),
                exchange.getNonceFactory(), signatureCreator);
        checkResult(result);
        return result.isSuccess();
    }

    BxTradeHistory[] getBxTradeHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
        String startDate = null;
        String endDate = null;
        if (tradeHistoryParams != null) {
            if (tradeHistoryParams instanceof BxTradeHistoryParams) {
                startDate = ((BxTradeHistoryParams) tradeHistoryParams).getStartDate();
                endDate = ((BxTradeHistoryParams) tradeHistoryParams).getEndDate();
            } else {
                throw new ExchangeException("Unsupported class of params!");
            }
        }
        BxTradeHistoryResult result = bx.getTradeHistory(null, TRADE, startDate,
                endDate, exchange.getExchangeSpecification().getApiKey(), exchange.getNonceFactory(),
                signatureCreator);
        return checkResult(result);
    }

    BxOrder[] getBxOrders() throws IOException {
        BxOrdersResult result = bx.getOrders(null, null,
                exchange.getExchangeSpecification().getApiKey(), exchange.getNonceFactory(), signatureCreator);
        return checkResult(result);
    }

    Collection<Order> getBxOrder(String... orderIds) throws IOException {
        List<Order> orders = new ArrayList<>();
        BxOrder[] bxOrders = getBxOrders();
        for (BxOrder order : bxOrders) {
            for (String orderId : orderIds) {
                if (order.getOrderId().equals(orderId)) {
                    orders.add(BxAdapters.adaptOrder(order));
                }
            }
        }
        return orders;
    }

    String placeBxLimitOrder(LimitOrder limitOrder) throws IOException {
        BxCreateOrderResult result = bx.createOrder(
                BxUtils.createBxCurrencyPair(limitOrder.getCurrencyPair()),
                BxAdapters.adaptOrderType(limitOrder.getType()),
                limitOrder.getOriginalAmount().toString(),
                limitOrder.getLimitPrice().toString(),
                exchange.getExchangeSpecification().getApiKey(),
                exchange.getNonceFactory(),
                signatureCreator);
        return checkResult(result);
    }

}
