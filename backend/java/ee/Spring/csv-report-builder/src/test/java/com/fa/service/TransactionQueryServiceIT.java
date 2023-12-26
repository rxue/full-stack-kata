package com.fa.service;

import com.fa.data.AccessToken;
import com.fa.data.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionQueryServiceIT extends AbstractIntegrationTest {

    @Test
    public void getPortfolio_idIs12_succeeded() {
        AccessToken accessToken = new AccessTokenProvider()
                .getToken(username, password)
                .block();
        List<Transaction> rs = new PortfolioQueryService()
                .query(accessToken, 12, new DateRange(LocalDate.of(2021,1,1), LocalDate.of(2021,12,31)))
                .block();
        Consumer<List<Transaction>> verify = result -> {
            assertTrue(result.size()>0);
            Transaction anyTransaction = result.stream().findAny().get();
            assertNotNull(anyTransaction.getPortfolioShortName());
            assertNotNull(anyTransaction.getSecurityName());
            assertNotNull(anyTransaction.getCurrencyCode());
            assertTrue(anyTransaction.getAmount() > 0, "amount has to be greater than 0");
            assertTrue(anyTransaction.getUnitPrice().compareTo(BigDecimal.ZERO) > 0, "unitPrice has to be greater than 0");
            assertTrue(anyTransaction.getTradeAmount().compareTo(BigDecimal.ZERO) > 0, "tradeAmount has to be greater than 0");
            assertNotNull(anyTransaction.getTransactionDate());
            assertNotNull(anyTransaction.getSettlementDate());
        };
        verify.accept(rs);
    }

    @Test
    public void getPortfolio_withDifferentDateRange() {
        AccessToken accessToken = new AccessTokenProvider()
                .getToken(username, password)
                .block();
        List<Transaction> rangedResult = new PortfolioQueryService()
                .query(accessToken, 12, new DateRange(LocalDate.of(2021,1,1), LocalDate.of(2021,12,31)))
                .block();
        List<Transaction> fullResult = new PortfolioQueryService()
                .query(accessToken, 12, new DateRange(null, null))
                .block();
        assertTrue(rangedResult.size() < fullResult.size());
    }
    @Test
    public void getPortfolio_withNonExistingId() {
        AccessToken accessToken = new AccessTokenProvider()
                .getToken(username, password)
                .block();
        List<Transaction> result = new PortfolioQueryService()
                .query(accessToken, 21000, new DateRange(LocalDate.of(2021,1,1), LocalDate.of(2021,12,31)))
                .block();
        assertTrue(result.isEmpty());
    }

}
