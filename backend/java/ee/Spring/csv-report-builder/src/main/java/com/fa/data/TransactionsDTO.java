package com.fa.data;

import lombok.Data;

import java.util.List;

@Data
public class TransactionsDTO {
    private List<Transaction> transactions;
}
