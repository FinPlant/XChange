package org.knowm.xchange.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.huobi.HuobiUtils;
import org.knowm.xchange.huobi.dto.account.HuobiAccount;
import org.knowm.xchange.huobi.dto.account.HuobiBalance;
import org.knowm.xchange.huobi.dto.account.results.HuobiAccountResult;
import org.knowm.xchange.huobi.dto.account.results.HuobiBalanceResult;

import java.io.IOException;

public class HuobiAccountServiceRaw extends HuobiBaseService {

    public HuobiAccountServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public HuobiBalance getHuobiBalance(String accountID) throws IOException {
        HuobiBalanceResult huobiBalanceResult = huobi.getBalance(
                accountID,
                exchange.getDefaultExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256, 2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        return checkResult(huobiBalanceResult);
    }

    public HuobiAccount[] getAccounts() throws IOException {
        HuobiAccountResult huobiAccountResult = huobi.getAccount(
                exchange.getDefaultExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256, 2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        return checkResult(huobiAccountResult);
    }

}
