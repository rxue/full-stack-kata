package com.fa;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class GraphQLClient {
    private WebClient.RequestBodySpec requestBodySpec;
    public GraphQLClient(String url) {
        requestBodySpec = initClient(url);
    }
    private static WebClient.RequestBodySpec initClient(String url) {
        return WebClient.create()
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON);
    }
    public void getResponse(GraphQLAccessToken accessToken) {
        Map<String, Object> body = getStringObjectMap();
        Mono<String> mono = requestBodySpec.header("Authorization", accessToken.toString())
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class);
                String output = mono.onErrorResume(err -> {
                   System.out.println(err);
                    return Mono.just("Default value");
                })
                .block();
    System.out.println("::::::::::::::::" + output);
    }

    private Map<String, Object> getStringObjectMap() {
        Map<String, Object> body = new HashMap<>();
        body.put("query", "query { portfolio(id:12) {shortName} }");
        return body;
    }

}
