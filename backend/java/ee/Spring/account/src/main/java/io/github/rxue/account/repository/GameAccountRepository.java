package io.github.rxue.account.repository;

import io.github.rxue.account.entity.GameAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAccountRepository extends JpaRepository<GameAccount,Long> {

}
