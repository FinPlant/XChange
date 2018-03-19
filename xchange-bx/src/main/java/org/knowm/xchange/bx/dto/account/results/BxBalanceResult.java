package org.knowm.xchange.bx.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bx.dto.BxResult;
import org.knowm.xchange.bx.dto.account.BxBalance;

public class BxBalanceResult extends BxResult<BxBalance> {

    public BxBalanceResult(@JsonProperty("data") BxBalance result, @JsonProperty("success") boolean success,
                           @JsonProperty("error") String error) {
        super(result, success, error);
    }

}
