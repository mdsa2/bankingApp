package com.BankingApp.mohammed.banking.repositry;

import com.BankingApp.mohammed.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositry extends JpaRepository<Account,Long> {
}
