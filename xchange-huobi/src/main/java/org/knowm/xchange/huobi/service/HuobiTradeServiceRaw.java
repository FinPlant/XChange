package org.knowm.xchange.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.huobi.HuobiUtils;
import org.knowm.xchange.huobi.dto.trade.HuobiOpenOrder;
import org.knowm.xchange.huobi.dto.trade.results.HuobiCancelOrderResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiOpenOrdersResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiOrderResult;

import java.io.IOException;

public class HuobiTradeServiceRaw extends HuobiBaseService {

    public HuobiTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public HuobiOpenOrder[] getHuobiOpenOrders() throws IOException {
        String states = "pre-submitted,submitted,partial-filled,partial-canceled,filled,canceled";
        HuobiOpenOrdersResult result = huobi.getOpenOrders(
                states,
                exchange.getDefaultExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256, 2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        return checkResult(result);
    }

    public String cancelHuobiOrder(String orderId) throws IOException {
        HuobiCancelOrderResult result = huobi.cancelOrder(
                orderId,
                exchange.getDefaultExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256, 2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        return checkResult(result);
    }

    public String placeHuobiLimitOrder(LimitOrder limitOrder) throws IOException {
        String type;
        if (limitOrder.getType() == OrderType.BID) {
            type = "buy-limit";
        } else if (limitOrder.getType() == OrderType.ASK) {
            type = "sell-limit";
        } else {
            throw new ExchangeException("Unsupported order type.");
        }
        HuobiOrderResult result = huobi.placeLimitOrder(
                limitOrder.getId(),
                limitOrder.getOriginalAmount().toString(),
                limitOrder.getLimitPrice().toString(),
                "api",
                HuobiUtils.createHuobiCurrencyPair(limitOrder.getCurrencyPair()),
                type,
                exchange.getDefaultExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256, 2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        return checkResult(result);
    }

    public String placeHuobiMarketOrder(MarketOrder limitOrder) throws IOException {
        String type;
        if (limitOrder.getType() == OrderType.BID) {
            type = "buy-market";
        } else if (limitOrder.getType() == OrderType.ASK) {
            type = "sell-market";
        } else {
            throw new ExchangeException("Unsupported order type.");
        }
        HuobiOrderResult result = huobi.placeMarketOrder(
                limitOrder.getId(),
                limitOrder.getOriginalAmount().toString(),
                "api",
                HuobiUtils.createHuobiCurrencyPair(limitOrder.getCurrencyPair()),
                type,
                exchange.getDefaultExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256, 2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        return checkResult(result);
    }

}
