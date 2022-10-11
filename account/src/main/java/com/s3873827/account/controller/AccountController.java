package com.s3873827.account.controller;

import com.s3873827.account.model.Account;
import com.s3873827.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    @Autowired
    AccountRepository repository;

    @GetMapping(path = "/", produces = "application/json")
    public List<Account> getUser() {
        return repository.findAll();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Account addUser(@RequestBody Account account) {
        return repository.save(account);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Optional<Account> getUser(@PathVariable String id) {
        return repository.findById(Long.valueOf(id));
    }

    @PutMapping("/{id}")
    Optional<Account> updateAccount(@RequestBody Account newAccount, @PathVariable String id) {
        Optional<Account> account = repository.findById(Long.valueOf(id));

        if (account.isPresent()) {
            if (!newAccount.getAccountType().equals(account.get().getAccountType()))
                account.get().setAccountType(newAccount.getAccountType());
            else if (!newAccount.getAccNumber().equals(account.get().getAccNumber()))
                account.get().setAccNumber(newAccount.getAccNumber());
            else if (!newAccount.getAccountName().equals(account.get().getAccountName()))
                account.get().setAccountName(newAccount.getAccountName());
            else if (!newAccount.getBalance().equals(account.get().getBalance()))
                account.get().setBalance(newAccount.getBalance());

            repository.save(account.get());
            return account;
        }

        return Optional.of(new Account());
    }

    @DeleteMapping("/{id}")
    Optional<Account> deleteAccount(@PathVariable String id) {
        Optional<Account> account = repository.findById(Long.valueOf(id));

        if (account.isPresent())
            repository.deleteById(Long.valueOf(id));

        return account;
    }


}
