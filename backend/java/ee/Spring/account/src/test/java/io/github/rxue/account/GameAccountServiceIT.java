package io.github.rxue.account;

import io.github.rxue.account.entity.Event;
import io.github.rxue.account.entity.GameAccount;
import io.github.rxue.account.entity.Type;
import io.github.rxue.account.repository.EventRepository;
import io.github.rxue.account.repository.GameAccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameAccountServiceIT {
    @Autowired
    private GameAccountRepository gameAccountRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private GameAccountService gameAccountService;
    @Test
    public void charge_succeeds() {
        Long accountId = 1L;
        final BigDecimal amount = BigDecimal.valueOf(10);
        Event event = new Event.Builder()
                .setId(1L)
                .setPlayerAccountId(accountId)
                .setType(Type.CHARGE)
                .setAmount(amount)
                .setTimestamp(ZonedDateTime.now())
                .build();
        final BigDecimal originalBalance = getAccountBalance(accountId);
        assertThat(eventRepository.findAll())
                .describedAs("Before event is handled, no event is expected in the repository")
                .isEmpty();
        BigDecimal result = gameAccountService.action(event);
        assertThat(result)
                .isEqualTo(originalBalance.subtract(amount));
        assertThat(eventRepository.findAll())
                .describedAs("After event is handled, there is expected to be one event in the repository")
                .hasSize(1);
    }
    @Test
    public void win() {
        Long accountId = 1L;
        final BigDecimal amount = BigDecimal.valueOf(10);
        Event event = new Event.Builder()
                .setId(1L)
                .setPlayerAccountId(accountId)
                .setType(Type.WIN)
                .setAmount(amount)
                .setTimestamp(ZonedDateTime.now())
                .build();
        final BigDecimal originalBalance = getAccountBalance(accountId);
        assertThat(eventRepository.findAll())
                .describedAs("Before event is handled, no event is expected in the repository")
                .isEmpty();
        BigDecimal result = gameAccountService.action(event);
        assertThat(result)
                .isEqualTo(originalBalance.add(amount));
        assertThat(eventRepository.findAll())
                .describedAs("After event is handled, there is expected to be one event in the repository")
                .hasSize(1);
    }
    @Test
    public void charge_repeats() {
        Long accountId = 1L;
        final BigDecimal amount = BigDecimal.valueOf(10);
        Event event = new Event.Builder()
                .setId(1L)
                .setPlayerAccountId(accountId)
                .setType(Type.CHARGE)
                .setAmount(amount)
                .setTimestamp(ZonedDateTime.now())
                .build();
        final BigDecimal originalBalance = getAccountBalance(accountId);
        assertThat(eventRepository.findAll())
                .describedAs("Before event is handled, no event is expected in the repository")
                .isEmpty();
        gameAccountService.action(event);
        BigDecimal result = gameAccountService.action(event);
        assertThat(result)
                .describedAs("Action should be idempotent in that no matter how many the same event is handled, the result should be the same")
                .isEqualTo(originalBalance.subtract(amount));
        assertThat(eventRepository.findAll())
                .describedAs("After event is handled more than once repeatedly, there is expected to be one event in the repository")
                .hasSize(1);
    }

    private BigDecimal getAccountBalance(Long accountId) {
        Optional<GameAccount> gameAccountOpt = gameAccountRepository.findById(accountId);
        return gameAccountOpt.orElseThrow(IllegalArgumentException::new)
                .getBalance();
    }
}