package com.fa.data;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Data
public class Transaction {
    private static final String NULL_PLACEHOLDER = "";
    private String portfolioShortName;
    private String securityName;
    private SecurityDTO security;
    private String currencyCode;
    private int amount;
    private BigDecimal unitPrice;
    private BigDecimal tradeAmount;
    private String typeName;
    private LocalDate transactionDate;
    private LocalDate settlementDate;
    public String toCSVRow(String delimiter) {
        List<String> values = new ArrayList<>();
        values.add(portfolioShortName);
        values.add(securityName);
        values.add(security == null ? NULL_PLACEHOLDER : security.getIsinCode());
        values.add(currencyCode);
        values.add(amount+"");
        values.add(unitPrice.toString());
        values.add(tradeAmount.toString());
        values.add(typeName);
        values.add(transactionDate.toString());
        values.add(settlementDate.toString());
        return values.stream()
                .collect(joining(delimiter));

    }
}
