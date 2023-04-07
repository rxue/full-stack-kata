package com.fa.service;

import com.fa.data.AccessToken;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

class AccessTokenProvider {
    private WebClient client = WebClient.create();
    public Mono<AccessToken> getToken(String userName, String password) {
        String url = "https://tryme.fasolutions.com/auth/realms/fa/protocol/openid-connect/token";
        MultiValueMap<String,String> requestFormBody = new LinkedMultiValueMap<>();
        requestFormBody.add("grant_type","password");
        requestFormBody.add("client_id","external-api");
        requestFormBody.add("username",userName);
        requestFormBody.add("password",password);
        return client.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(requestFormBody))
                .retrieve()
                .bodyToMono(AccessToken.class);
    }
}
