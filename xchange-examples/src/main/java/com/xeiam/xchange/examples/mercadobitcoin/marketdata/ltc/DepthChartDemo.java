package com.xeiam.xchange.examples.mercadobitcoin.marketdata.ltc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.knowm.xchart.ChartBuilder_XY;
import org.knowm.xchart.Chart_XY;
import org.knowm.xchart.Series_XY;
import org.knowm.xchart.Series_XY.ChartXYSeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.style.markers.SeriesMarkers;

import com.xeiam.xchange.Exchange;
import com.xeiam.xchange.ExchangeFactory;
import com.xeiam.xchange.currency.Currency;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.dto.marketdata.OrderBook;
import com.xeiam.xchange.dto.trade.LimitOrder;
import com.xeiam.xchange.mercadobitcoin.MercadoBitcoinExchange;
import com.xeiam.xchange.service.polling.marketdata.PollingMarketDataService;

/**
 * Demonstrate requesting OrderBook from Mercado Bitcoin and plotting it using XChart.
 *
 * @author Copied from Bitstamp and adapted by Felipe Micaroni Lalli
 */
public class DepthChartDemo {

  public static void main(String[] args) throws IOException {

    // Use the factory to get the version 1 Mercado Bitcoin exchange API using default settings
    Exchange mercadoExchange = ExchangeFactory.INSTANCE.createExchange(MercadoBitcoinExchange.class.getName());

    // Interested in the public market data feed (no authentication)
    PollingMarketDataService marketDataService = mercadoExchange.getPollingMarketDataService();

    System.out.println("fetching data...");

    // Get the current orderbook
    OrderBook orderBook = marketDataService.getOrderBook(new CurrencyPair(Currency.LTC, Currency.BRL));

    System.out.println("received data.");

    System.out.println("plotting...");

    // Create Chart
    Chart_XY chart = new ChartBuilder_XY().width(800).height(600).title("Mercado Order Book").xAxisTitle("LTC").yAxisTitle("BRL").build();

    // Customize Chart
    chart.getStyler().setDefaultSeriesRenderStyle(ChartXYSeriesRenderStyle.Area);

    // BIDS
    List<Number> xData = new ArrayList<Number>();
    List<Number> yData = new ArrayList<Number>();
    BigDecimal accumulatedBidUnits = new BigDecimal("0");
    for (LimitOrder limitOrder : orderBook.getBids()) {
      if (limitOrder.getLimitPrice().doubleValue() > 0) {
        xData.add(limitOrder.getLimitPrice());
        accumulatedBidUnits = accumulatedBidUnits.add(limitOrder.getTradableAmount());
        yData.add(accumulatedBidUnits);
      }
    }
    Collections.reverse(xData);
    Collections.reverse(yData);

    // Bids Series
    Series_XY series = chart.addSeries("bids", xData, yData);
    series.setMarker(SeriesMarkers.NONE);

    // ASKS
    xData = new ArrayList<Number>();
    yData = new ArrayList<Number>();
    BigDecimal accumulatedAskUnits = new BigDecimal("0");
    for (LimitOrder limitOrder : orderBook.getAsks()) {
      if (limitOrder.getLimitPrice().doubleValue() < 200) {
        xData.add(limitOrder.getLimitPrice());
        accumulatedAskUnits = accumulatedAskUnits.add(limitOrder.getTradableAmount());
        yData.add(accumulatedAskUnits);
      }
    }

    // Asks Series
    series = chart.addSeries("asks", xData, yData);
    series.setMarker(SeriesMarkers.NONE);

    new SwingWrapper(chart).displayChart();

  }

}
