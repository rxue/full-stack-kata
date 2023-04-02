package com.fa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PortfolioClient {
    public static void main(String[] args) {
        String userName = args[0];
        String password = args[1];
        SpringApplication.run(PortfolioClient.class, args);
        WebClient webClient = WebClient.create();
        String url = "https://tryme.fasolutions.com/auth/realms/fa/protocol/openid-connect/token";
        MultiValueMap<String,String> requestFormBody = new LinkedMultiValueMap<>();
        requestFormBody.add("grant_type","password");
        requestFormBody.add("client_id","external-api");
        requestFormBody.add("username",userName);
        requestFormBody.add("password",password);
        String responseToken = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(requestFormBody))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        ObjectMapper mapper = new ObjectMapper();
        try {
            GraphQLAccessToken token = mapper.readValue(responseToken, GraphQLAccessToken.class);
            GraphQLClient graphQLClient = new GraphQLClient("https://tryme.fasolutions.com/graphql");
            graphQLClient.getResponse(token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
