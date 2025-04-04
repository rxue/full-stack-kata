package io.github.rxue.account;

import io.github.rxue.account.entity.Event;
import io.github.rxue.account.entity.GameAccount;
import io.github.rxue.account.entity.Type;
import io.github.rxue.account.repository.EventRepository;
import io.github.rxue.account.repository.GameAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class GameAccountService {
    private final EventRepository eventRepository;
    private final GameAccountRepository gameAccountRepository;
    public GameAccountService(EventRepository eventRepository, GameAccountRepository gameAccountRepository) {
        this.eventRepository = eventRepository;
        this.gameAccountRepository = gameAccountRepository;
    }

    @Transactional
    public BigDecimal action(Event event) {
        Optional<Event> eventOptional = eventRepository.findById(event.getId());
        final Long playerAccountId = event.getPlayerAccountId();
        GameAccount gameAccount = gameAccountRepository.findById(playerAccountId)
                .orElseThrow(() -> new AccountNotFoundException(playerAccountId));
        if (eventOptional.isEmpty()) {
            BigDecimal remainingBalance = getRemainingBalance(gameAccount, event.getAmount(), event.getType());
            gameAccount.setBalance(remainingBalance);
            gameAccountRepository.save(gameAccount);
            eventRepository.save(event);
            return remainingBalance;
        } else {
            return gameAccount.getBalance();
        }
    }

    static BigDecimal getRemainingBalance(GameAccount gameAccount, BigDecimal amount, Type type) {
        BigDecimal balance = gameAccount.getBalance();
        if (type == Type.CHARGE) {
            BigDecimal remaining = balance.subtract(amount);
            if (remaining.compareTo(BigDecimal.ZERO) >= 0)
                return remaining;
            else
                throw new InsufficientBalanceException(balance, amount);
        } else if (type == Type.WIN){
            return balance.add(amount);
        }
        throw new UnsupportedOperationException("Other operations not supported yet");
    }


}
