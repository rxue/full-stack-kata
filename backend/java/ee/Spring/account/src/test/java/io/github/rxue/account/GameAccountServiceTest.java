package io.github.rxue.account;

import io.github.rxue.account.entity.GameAccount;
import io.github.rxue.account.entity.Type;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameAccountServiceTest {
    @Test
    public void getRemainingBalance_successful_charge() {

        GameAccount gameAccount = new GameAccount(1L);
        gameAccount.setName("test");
        final BigDecimal balance = BigDecimal.valueOf(100);
        gameAccount.setBalance(balance);
        final BigDecimal amount = BigDecimal.valueOf(10);
        BigDecimal remainingBalance = GameAccountService.getRemainingBalance(gameAccount, amount, Type.CHARGE);
        assertThat(remainingBalance)
                .isEqualTo(balance.subtract(amount));
    }
    @Test
    public void getRemainingBalance_win() {
        GameAccount gameAccount = new GameAccount(1L);
        gameAccount.setName("test");
        final BigDecimal balance = BigDecimal.valueOf(100);
        gameAccount.setBalance(balance);
        final BigDecimal amount = BigDecimal.valueOf(10);
        BigDecimal remainingBalance = GameAccountService.getRemainingBalance(gameAccount, amount, Type.WIN);
        assertThat(remainingBalance)
                .isEqualTo(balance.add(amount));
    }

    @Test
    public void getRemainingBalance_charge_failure() {
        GameAccount gameAccount = new GameAccount(1L);
        gameAccount.setName("test");
        final BigDecimal balance = BigDecimal.valueOf(100);
        gameAccount.setBalance(balance);
        final BigDecimal amount = BigDecimal.valueOf(10000);
        assertThrows(InsufficientBalanceException.class, () -> GameAccountService.getRemainingBalance(gameAccount, amount, Type.CHARGE));
    }
}