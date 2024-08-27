package com.BankingApp.mohammed.banking.service;

import com.BankingApp.mohammed.banking.dto.AccountMapper;
import com.BankingApp.mohammed.banking.dto.AccountDto;
import com.BankingApp.mohammed.banking.entity.Account;
import com.BankingApp.mohammed.banking.repositry.AccountRepositry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepositry accountRepositry;
    public AccountServiceImpl(AccountRepositry accountRepositry) {
        this.accountRepositry = accountRepositry;
    }

    @Override
    public AccountDto CreateAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepositry.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto GetById(Long Id) {
     Account account =   accountRepositry.findById(Id).orElseThrow(()->new RuntimeException("Account Id not found"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id , double amount) {
        Account account =   accountRepositry.findById(id).orElseThrow(()->new RuntimeException("Account Id not found"));
        double totalAmount = account.getBalance() + amount;
        account.setBalance(totalAmount);
        Account savedAccount = accountRepositry.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long Id, double amount) {
        Account account = accountRepositry.findById(Id).orElseThrow(()->new RuntimeException("Account Id not found"));
        if (account.getBalance() - amount < 0) {
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepositry.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> GetAllAccounts() {
        List<Account> accounts = accountRepositry.findAll();
      return   accounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long Id) {
        Account account = accountRepositry.findById(Id).orElseThrow(()->new RuntimeException("Account Id not found"));
        accountRepositry.delete(account);
    }
}
