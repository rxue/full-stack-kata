package com.fa.service;

import com.fa.data.AccessToken;
import com.fa.data.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
public class TransactionQueryServiceIT {
    private static String username;
    private static String password;
    @BeforeAll
    public static void init() {
        username = TestUtil.getProperty("username");
        password = TestUtil.getProperty("password");
    }

    @Test
    public void getPortfolio_idIs12_succeeded() {
        AccessToken accessToken = new AccessTokenProvider()
                .getToken(username, password)
                .block();
        List<Transaction> result = new PortfolioQueryService()
                .query(accessToken, 12, new DateRange(LocalDate.of(2021,1,1), LocalDate.of(2021,12,31)))
                .block();
        verify(result);
    }

    private void verify(List<Transaction> result) {
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
    }
}
