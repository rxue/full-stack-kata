package com.fa.service;

import com.fa.data.AccessToken;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class TransactionQueryServiceFacadeIT extends AbstractIntegrationTest {
    @Test
    public void getPortfolio_idIs12_succeeded() {
        byte[] result = new PortfolioQueryServiceFacade()
                .query(username, password, 12, LocalDate.of(2021,1,1), LocalDate.of(2021,12,31))
                .block();
        assertTrue(result.length > 0);
    }
    @Test
    public void getPortfolio_withWrongPassword() {
        assertThrows(Exception.class, () -> new PortfolioQueryServiceFacade()
                .query(username, "x", 12, LocalDate.of(2021,1,1), LocalDate.of(2021,12,31))
                .block());
    }

    @Test
    public void getPortfolio_withInvalidToken() {
        AccessToken invalidToken = new AccessToken();
        invalidToken.setTokenType("Bearer");
        invalidToken.setTokenType("xxx");
        byte[] result = new PortfolioQueryServiceFacade()
                .query(invalidToken, 12, LocalDate.of(2021,1,1), LocalDate.of(2021,12,31))
                .block();
        assertNull(result);
    }
}
