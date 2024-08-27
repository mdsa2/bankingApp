package com.BankingApp.mohammed.banking.controller;

import com.BankingApp.mohammed.banking.dto.AccountDto;
import com.BankingApp.mohammed.banking.dto.DepositRequest;
import com.BankingApp.mohammed.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.CreateAccount(accountDto), HttpStatus.CREATED);
    }
 @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id) {
        AccountDto accountDto = accountService.GetById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody DepositRequest request) {
        Double amount = request.getAmount();
        if (amount == null) {
            return ResponseEntity.badRequest().body("Amount is required and must be a valid number");
        }
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawal(@PathVariable Long id, @RequestBody DepositRequest request) {
        double amount = request.getAmount();
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.GetAllAccounts();
        return ResponseEntity.ok(accountDtos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(Long Id){
        accountService.deleteAccount(Id);
        return ResponseEntity.ok("Account deleted Successfully");
    }
}
