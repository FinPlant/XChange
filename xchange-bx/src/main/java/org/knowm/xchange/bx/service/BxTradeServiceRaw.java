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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BxTradeServiceRaw extends BxBaseService {

    public BxTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public boolean cancelBxOrder(String orderId) throws IOException {
        BxCancelOrderResult result = bx.cancelOrder(null, orderId, exchange.getExchangeSpecification().getApiKey(),
                exchange.getNonceFactory(), signatureCreator);
        checkResult(result);
        return result.isSuccess();
    }

    public void getBxTradeHistory() throws IOException {
        BxTradeHistoryResult result = bx.getTradeHistory(null, null, null,
                null, exchange.getExchangeSpecification().getApiKey(), exchange.getNonceFactory(),
                signatureCreator);
        BxTradeHistory[] tradeHistories = checkResult(result);
        for (BxTradeHistory tradeHistory : tradeHistories) {
            System.out.println(tradeHistory);
        }
    }

    public BxOrder[] getBxOrders() throws IOException {
        BxOrdersResult result = bx.getOrders(null, null,
                exchange.getExchangeSpecification().getApiKey(), exchange.getNonceFactory(), signatureCreator);
        return checkResult(result);
    }

    public Collection<Order> getBxOrder(String... orderIds) throws IOException {
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

    public String placeBxLimitOrder(LimitOrder limitOrder) throws IOException {
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
