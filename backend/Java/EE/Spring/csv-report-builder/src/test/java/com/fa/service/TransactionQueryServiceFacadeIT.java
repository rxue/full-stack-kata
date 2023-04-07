package com.fa.service;

import com.fa.data.AccessToken;
import com.fa.data.SecurityDTO;
import com.fa.data.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig
public class TransactionQueryServiceFacadeIT {
    private static String userName;
    private static String password;
    @BeforeAll
    public static void init() {
        userName = System.getenv("username");
        password = System.getenv("password");
    }

    @Test
    public void getPortfolio_idIs12_succeeded() {
        byte[] result = new PortfolioQueryServiceFacade()
                .query(userName, password, 12, LocalDate.of(2021,1,1), LocalDate.of(2021,12,31))
                .block();
        assertTrue(result.length > 0);
    }
}
