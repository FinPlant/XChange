package org.knowm.xchange.bx;

import org.knowm.xchange.bx.dto.marketdata.BxAssetPair;
import org.knowm.xchange.bx.dto.marketdata.BxTicker;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import si.mazi.rescu.SynchronizedValueFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BxAdapters {

    public static ExchangeMetaData adaptToExchangeMetaData(Map<String, BxAssetPair> assetPairs) {
        BxUtils.setBxAssetPairs(assetPairs);
        Map<CurrencyPair, CurrencyPairMetaData> pairs = new HashMap<>();
        for (String id : assetPairs.keySet()) {
            if (assetPairs.get(id).isActive()) {
                pairs.put(adaptCurrencyPair(id), adaptCurrencyPairMetaData(assetPairs.get(id)));
            }
        }
        return new ExchangeMetaData(pairs, BxUtils.getCurrencies(), null, null,
                false);
    }

    private static CurrencyPair adaptCurrencyPair(String pairId) {
        return BxUtils.translateBxCurrencyPair(pairId);
    }

    private static CurrencyPairMetaData adaptCurrencyPairMetaData(BxAssetPair assetPair) {
        return new CurrencyPairMetaData(null, assetPair.getPrimaryMin(), null, 0);
    }

    public static Ticker adaptTicker(BxTicker bxTicker, SynchronizedValueFactory<Long> nonce) {
        Ticker.Builder builder = new Ticker.Builder();
        builder.currencyPair(BxUtils.translateBxCurrencyPair(bxTicker.getPairingId()));
        builder.open(bxTicker.getOpen());
        builder.last(bxTicker.getLastPrice());
        builder.bid(bxTicker.getOrderBook().getBids().getHighBid());
        builder.ask(bxTicker.getOrderBook().getAsks().getHighBid());
        builder.high(bxTicker.getHigh());
        builder.low(bxTicker.getLow());
        builder.vwap(bxTicker.getAvg());
        builder.volume(bxTicker.getVolume24hours());
        builder.timestamp(new Date(nonce.createValue()));
        return builder.build();
    }

}
