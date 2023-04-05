package com.fa.service;

import com.fa.data.Portfolio;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PortfolioQueryServiceFacade {
    public Mono<Portfolio> query(String userName, String password, int portfolioId) {
        return null;
    }
}
