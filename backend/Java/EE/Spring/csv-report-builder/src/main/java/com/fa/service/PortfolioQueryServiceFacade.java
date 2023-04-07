package com.fa.service;

import com.fa.data.Transaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class PortfolioQueryServiceFacade {
    private final AccessTokenProvider accessTokenProvider;
    private final PortfolioQueryService queryService;
    public PortfolioQueryServiceFacade() {
        this.accessTokenProvider = new AccessTokenProvider();
        this.queryService = new PortfolioQueryService();
    }
    public Mono<byte[]> query(String userName, String password, long portfolioId, LocalDate startDate, LocalDate endDate) {
        return accessTokenProvider.getToken(userName, password)
                .flatMap(t -> queryService.query(t, portfolioId, new DateRange(startDate, endDate)))
                .map(PortfolioQueryServiceFacade::toStringList)
                .map(stringList -> stringList.stream().collect(joining("\n")))
                .map(String::getBytes);
    }
    private static List<String> toStringList(List<Transaction> transactions) {
        List<String> csvContent = new ArrayList<>();
        csvContent.add(Util.getFieldNames("isinCode", "\t"));
        csvContent.addAll(transactions.stream().map(t -> t.toCSVRow("\t")).collect(toList()));
        return csvContent;
    }
}