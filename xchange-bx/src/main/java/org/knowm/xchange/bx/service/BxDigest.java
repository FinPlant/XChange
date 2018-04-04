package org.knowm.xchange.bx.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.ws.rs.FormParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class BxDigest extends BaseParamsDigest {

    private final String secretKey;
    private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private BxDigest(String secretKey) {
        super(secretKey, HMAC_SHA_256);
        this.secretKey = secretKey;
    }

    static BxDigest createInstance(String secretKey) {
        return secretKey == null ? null : new BxDigest(secretKey);
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {
        String apiKey = restInvocation.getParamValue(FormParam.class, "key").toString();
        String nonce = restInvocation.getParamValue(FormParam.class, "nonce").toString();
        String signature = apiKey + nonce + secretKey;
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Illegal algorithm for post body digest. Check the implementation.");
        }
        sha256.update(signature.getBytes());
        signature = new String(encodeHex(sha256.digest()));
        return signature;
    }

    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

}
