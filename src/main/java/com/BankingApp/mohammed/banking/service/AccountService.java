package com.BankingApp.mohammed.banking.service;

import com.BankingApp.mohammed.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto CreateAccount(AccountDto accountDto);
    AccountDto GetById(Long Id);
    AccountDto deposit(Long Id , double amount);
    AccountDto withdraw(Long Id , double amount);
    List<AccountDto> GetAllAccounts();
    void deleteAccount(Long Id);
}
