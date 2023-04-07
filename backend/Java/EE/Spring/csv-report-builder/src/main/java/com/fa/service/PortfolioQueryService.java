package com.fa.service;

import com.fa.data.AccessToken;
import com.fa.data.DataDTO;
import com.fa.data.Transaction;
import com.fa.data.TransactionsDTO;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.fa.service.Util.getFieldNames;
import static java.util.stream.Collectors.joining;

class PortfolioQueryService {
    public Mono<List<Transaction>> query(AccessToken accessToken, long id, DateRange dateRange) {
        WebClient.RequestBodySpec requestBodySpec = WebClient.create()
                .post()
                .uri("https://tryme.fasolutions.com/graphql")
                .contentType(MediaType.APPLICATION_JSON);
        return requestBodySpec.header("Authorization", accessToken.toString())
                .body(BodyInserters.fromValue(new GraphQLRequestBody(id, dateRange.getStartDate(), dateRange.getEndDate())))
                .retrieve()
                .bodyToMono(DataDTO.class)
                .map(DataDTO::getData)
                .map(TransactionsDTO::getTransactions);
    }
    @Data
    private class GraphQLRequestBody {
        private static final String PORTFOLIO_ID = "portfolioId";
        private static final String START_DATE = "startDate";
        private static final String END_DATE = "endDate";
        private String query;
        private Map<String, Object> variables;
        Supplier<String> variableTypeDefinitions = () -> {
            BinaryOperator<String> getVariableTypeDef = (variableName, type) -> "$" + variableName + ": " + type;
            List<String> variableTypes = new ArrayList<>();
            variableTypes.add(getVariableTypeDef.apply(PORTFOLIO_ID, "Long"));
            variableTypes.add(getVariableTypeDef.apply(START_DATE, "String"));
            variableTypes.add(getVariableTypeDef.apply(END_DATE, "String"));
            return variableTypes.stream().collect(joining(","));
        };
        Supplier<String> variableDeclarations = () -> {
            UnaryOperator<String> getVariableDeclaration = variableName -> variableName + ": $" + variableName;
            List<String> variableTypes = new ArrayList<>();
            variableTypes.add(getVariableDeclaration.apply(PORTFOLIO_ID));
            variableTypes.add(getVariableDeclaration.apply(START_DATE));
            variableTypes.add(getVariableDeclaration.apply(END_DATE));
            return variableTypes.stream().collect(joining(","));
        };
        Supplier<String> graphQLFieldNames = () -> getFieldNames("security{isinCode}", ",");
        public GraphQLRequestBody(long id, String startDate, String endDate) {
            this.query = new StringBuilder()
                .append("query(" + variableTypeDefinitions.get() + ")")
                .append("{")
                .append("transactions(" +variableDeclarations.get() + ")")
                .append("{" + graphQLFieldNames.get() + "}")
                .append("}")
                .toString();
            this.variables = new HashMap<>();
            this.variables.put(PORTFOLIO_ID, id);
            this.variables.put(START_DATE, startDate);
            this.variables.put(END_DATE, endDate);
        }
    }
}
