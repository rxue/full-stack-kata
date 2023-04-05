package com.fa.service;

import com.fa.data.AccessToken;
import com.fa.data.DataDTO;
import com.fa.data.Portfolio;
import com.fa.data.PortfolioHolder;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

class PortfolioQueryService {
    public Mono<Portfolio> query(AccessToken accessToken, int id) {
        WebClient.RequestBodySpec requestBodySpec = WebClient.create()
                .post()
                .uri("https://tryme.fasolutions.com/graphql")
                .contentType(MediaType.APPLICATION_JSON);
        return requestBodySpec.header("Authorization", accessToken.toString())
                .body(BodyInserters.fromValue(new GraphQLRequestBody(id)))
                .retrieve()
                .bodyToMono(DataDTO.class)
                .map(DataDTO::getData)
                .map(PortfolioHolder::getPortfolio);
    }
    @Data
    private class GraphQLRequestBody {
        private String query;
        private Map<String, Object> variables;
        public GraphQLRequestBody(long id) {
            this.query = "query($id: Long) { portfolio(id: $id) {shortName} }";
            this.variables = new HashMap<>();
            this.variables.put("id", id);
        }
    }
}
