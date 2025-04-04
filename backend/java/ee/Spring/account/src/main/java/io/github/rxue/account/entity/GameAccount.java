package io.github.rxue.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class GameAccount {
    @Id
    private Long id;
    private String name;
    private BigDecimal balance;

    public GameAccount(Long id) {
        this.id = id;
    }

    public GameAccount() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
