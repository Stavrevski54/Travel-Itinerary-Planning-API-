package com.example.travelitineraryapi.service;

import com.example.travelitineraryapi.entity.Account;
import com.example.travelitineraryapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createUser(Account user) {
        return accountRepository.save(user);
    }

    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    public Optional<Account> getUserById(Long id) {
        return accountRepository.findById(id);
    }

    public Account updateUser(Long id, Account userDetails) {
        Optional<Account> optionalUser = accountRepository.findById(id);
        if (optionalUser.isPresent()) {
            Account user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            return accountRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public void deleteUser(Long id) {
        accountRepository.deleteById(id);
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }
}
