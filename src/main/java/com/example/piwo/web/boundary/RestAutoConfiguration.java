package com.example.piwo.web.boundary;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;

@ConditionalOnMissingBean(RestOperations.class)
@Configuration
public class RestAutoConfiguration {

    @Bean
    public RestOperations restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        restTemplate.setErrorHandler(defaultResponseErrorHandler());
        return restTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        int timeout = 20000;
        factory.setReadTimeout(timeout);
        factory.setConnectTimeout(timeout);
        factory.setOutputStreaming(false);
        return factory;
    }

    private DefaultResponseErrorHandler defaultResponseErrorHandler() {
        return new DefaultResponseErrorHandler() {

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                    throw new RuntimeException(response.getStatusText() + " " + StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
                }
                super.handleError(response);
            }
        };
    }
}
