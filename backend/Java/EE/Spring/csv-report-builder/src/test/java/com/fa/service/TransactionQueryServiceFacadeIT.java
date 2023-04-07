package com.fa.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig
public class TransactionQueryServiceFacadeIT {
    private static String username;
    private static String password;
    @BeforeAll
    public static void init() {
        username = TestUtil.getProperty("username");
        password = TestUtil.getProperty("password");
    }

    @Test
    public void getPortfolio_idIs12_succeeded() {
        byte[] result = new PortfolioQueryServiceFacade()
                .query(username, password, 12, LocalDate.of(2021,1,1), LocalDate.of(2021,12,31))
                .block();
        assertTrue(result.length > 0);
    }
}
