package org.knowm.xchange.bx.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bx.dto.account.BxBalance;
import org.knowm.xchange.bx.dto.account.results.BxBalanceResult;

import java.io.IOException;
import java.util.Map;

public class BxAccountServiceRaw extends BxBaseService {

    public BxAccountServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public void getBxBalance() throws IOException {
        BxBalanceResult result = bx.getBalance(exchange.getExchangeSpecification().getApiKey(),
                exchange.getNonceFactory(), signatureCreator);
        Map<String, BxBalance> balanceMap = checkResult(result);
        for (String key : balanceMap.keySet()) {
            System.out.println(String.format("%s %s", key, balanceMap.get(key)));
        }
    }

}
