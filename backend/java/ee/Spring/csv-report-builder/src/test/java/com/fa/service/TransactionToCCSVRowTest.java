package com.fa.service;

import com.fa.data.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TransactionToCCSVRowTest {
    @Test
    public void securityMising() {
        Transaction tested = new Transaction();
        tested.setSecurityName("shortName");
        tested.setTradeAmount(BigDecimal.ONE);
    }
}