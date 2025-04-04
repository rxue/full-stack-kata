package io.github.rxue.account;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(BigDecimal balance, BigDecimal chargeAmount) {
        super("Charge amount is " + chargeAmount + ", it is bigger than the account balance " + balance);
    }
}
