package com.fa.service;

import com.fa.data.AccessToken;
import com.fa.data.Transaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Service
public class PortfolioQueryServiceFacade {
    private final AccessTokenProvider accessTokenProvider;
    private final PortfolioQueryService queryService;
    private Map<String, AccessToken> tokens;
    public PortfolioQueryServiceFacade() {
        this.accessTokenProvider = new AccessTokenProvider();
        this.queryService = new PortfolioQueryService();
        this.tokens = new ConcurrentHashMap<>();
    }
    public Mono<byte[]> query(String username, String password, long portfolioId, LocalDate startDate, LocalDate endDate) {
        return accessTokenProvider.getToken(username, password)
                .flatMap(t -> {
                    System.out.println("got token:" + t.getAccessToken());
                    return queryService.query(t, portfolioId, new DateRange(startDate, endDate));
                })
                .map(PortfolioQueryServiceFacade::toBytes);
    }
    public Mono<byte[]> query(AccessToken accessToken, long portfolioId, LocalDate startDate, LocalDate endDate) {
        return queryService.query(accessToken, portfolioId, new DateRange(startDate, endDate))
                .map(PortfolioQueryServiceFacade::toBytes);
    }
    private static byte[] toBytes(List<Transaction> transactions) {
        List<String> csvContentRows = new ArrayList<>();
        csvContentRows.add(Util.getFieldNames("isinCode", "\t"));
        csvContentRows.addAll(transactions.stream().map(t -> t.toCSVRow("\t")).collect(toList()));
        return csvContentRows.stream()
                .collect(joining("\n", "", "\n"))
                .getBytes();
    }
}