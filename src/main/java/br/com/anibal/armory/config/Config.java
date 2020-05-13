package br.com.anibal.armory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    public static final String PRICE_SERVER_URL = "http://localhost:8082/";

    final Logger logger = LoggerFactory.getLogger(Config.class);

    @Bean
    public WebClient priceWebClient(WebClient.Builder builder) {
        return builder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(PRICE_SERVER_URL)
                .filter(logPriceWebRequests())
                .build();
    }

    private ExchangeFilterFunction logPriceWebRequests() {
        return ((request, next) -> {
            logger.info("PriceWebClient - Request: {} {}", request.method(), request.url());
            return next.exchange(request);
        });
    }
}
