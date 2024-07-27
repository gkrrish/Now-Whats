package com.whats.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WhatsAppServiceClient {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.api.token}")
    private String apiToken;

    public String sendMessage(String to, String message) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(apiUrl);

            httpPost.setHeader("Authorization", "Bearer " + apiToken);
            httpPost.setHeader("Content-Type", "application/json");

            String json = "{ \"messaging_product\": \"whatsapp\", \"to\": \"" + to + "\", \"type\": \"text\", \"text\": { \"body\": \"" + message + "\" } }";

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = client.execute(httpPost)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
}
