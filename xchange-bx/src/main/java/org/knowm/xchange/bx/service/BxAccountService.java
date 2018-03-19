package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BxAccountService extends BxAccountServiceRaw implements AccountService {

    public BxAccountService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public AccountInfo getAccountInfo() throws IOException {
        return null;
    }

    @Override
    public String withdrawFunds(Currency currency, BigDecimal bigDecimal, String s) throws IOException {
        return null;
    }

    @Override
    public String withdrawFunds(WithdrawFundsParams withdrawFundsParams) throws IOException {
        return null;
    }

    @Override
    public String requestDepositAddress(Currency currency, String... strings) throws IOException {
        return null;
    }

    @Override
    public TradeHistoryParams createFundingHistoryParams() {
        return null;
    }

    @Override
    public List<FundingRecord> getFundingHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
        return null;
    }

}
