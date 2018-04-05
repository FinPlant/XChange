package org.knowm.xchange.bx;

import org.knowm.xchange.exceptions.ExchangeException;

import java.io.*;
import java.util.Properties;

class BxProperties {

    private String apiKey;
    private String secretKey;
    private final String fileName = "bx-secret.keys";
    private final String API = "api-key";
    private final String SECRET = "secret-key";

    public BxProperties() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            createFile();
        }
        Properties properties = new Properties();
        InputStream input = new FileInputStream(fileName);
        properties.load(input);
        apiKey = properties.getProperty(API);
        secretKey = properties.getProperty(SECRET);
        input.close();
    }

    private void createFile() throws IOException {
        Properties properties = new Properties();
        OutputStream output = new FileOutputStream(fileName);
        properties.setProperty(API, "");
        properties.setProperty(SECRET, "");
        properties.store(output, null);
        output.close();
    }

    public String getApiKey() {
        if (apiKey.isEmpty()) {
            throw new ExchangeException("Please specify " + API + " in the file " + fileName + ".");
        }
        return apiKey;
    }

    public String getSecretKey() {
        if (secretKey.isEmpty()) {
            throw new ExchangeException("Please specify " + SECRET + " in the file " + fileName + ".");
        }
        return secretKey;
    }

}
