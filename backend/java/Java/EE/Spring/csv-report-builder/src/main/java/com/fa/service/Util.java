package com.fa.service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

class Util {
    private Util() {}
    static String getFieldNames(String isinCode, String delimiter) {
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("portfolioShortName");
        fieldNames.add("securityName");
        fieldNames.add(isinCode);
        fieldNames.add("currencyCode");
        fieldNames.add("amount");
        fieldNames.add("unitPrice");
        fieldNames.add("tradeAmount");
        fieldNames.add("typeName");
        fieldNames.add("transactionDate");
        fieldNames.add("settlementDate");
        return fieldNames.stream().collect(joining(delimiter));
    }
}
