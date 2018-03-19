package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bx.dto.account.results.BxBalanceResult;

import java.io.IOException;

public class BxAccountServiceRaw extends BxBaseService {

    public BxAccountServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public void getBxBalance() throws IOException {
        BxBalanceResult result = bx.getBalance(exchange.getExchangeSpecification().getApiKey(),
                exchange.getNonceFactory(), signatureCreator);
        checkResult(result);
    }

}
