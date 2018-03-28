package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bx.dto.trade.BxTradeHistory;
import org.knowm.xchange.bx.dto.trade.results.BxCancelOrderResult;
import org.knowm.xchange.bx.dto.trade.results.BxOrdersResult;
import org.knowm.xchange.bx.dto.trade.results.BxTradeHistoryResult;

import java.io.IOException;

public class BxTradeServiceRaw extends BxBaseService {

    public BxTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public void cancelBxOrder(String pairId, String orderId) throws IOException {
        BxCancelOrderResult result = bx.cancelOrder(pairId, orderId, exchange.getExchangeSpecification().getApiKey(),
                exchange.getNonceFactory(), signatureCreator);
        checkResult(result);
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

    public void getBxOrders() throws IOException {
        BxOrdersResult result = bx.getOrders(null, null,
                exchange.getExchangeSpecification().getApiKey(), exchange.getNonceFactory(), signatureCreator);
        checkResult(result);
    }

}
