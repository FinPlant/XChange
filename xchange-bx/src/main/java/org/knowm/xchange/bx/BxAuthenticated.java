package org.knowm.xchange.bx;

import org.knowm.xchange.bx.dto.account.results.BxBalanceResult;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/api/")
@Produces(MediaType.APPLICATION_JSON)
public interface BxAuthenticated extends Bx {

    @POST
    @Path("balance/")
    BxBalanceResult getBalance(@FormParam("key") String apiKey,
                               @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
                               @FormParam("signature") ParamsDigest signature) throws IOException;

}
