package com.oceanapplication.ocean.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oceanapplication.ocean.repo.AccountRepository;
import com.oceanapplication.ocean.models.Account;

import java.util.Optional;

@Controller
@RequestMapping(path="/demo")
public class AuthorizationController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path="/signUp")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String phoneNumber) {

        Account n = new Account();
        n.setName(name);
        n.setPhoneNumber(phoneNumber);
        accountRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }

    @GetMapping(path="/signIn")
    public String logIn(@RequestParam String phoneNumber) {
        checkAccountInDatabase();
    }

    private Long checkAccountInDatabase(String phoneNumber) {
        // Iterate all fields in database and compare currant phoneNumber with phoneNumber in database
        for (long userId = 1; userId < accountRepository.count(); userId++) {
            Optional<Account> optionalAccount = accountRepository.findById(userId);
            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                if (comparePhoneNumbers(phoneNumber, account)){
                    return account.getId();
                }
            }
        }
        return null;
    }

    private Boolean comparePhoneNumbers(String phoneNumber, Account account){
        String phoneNumberFromDB = account.getPhoneNumber();
        int flag = phoneNumberFromDB.compareTo(phoneNumber);
    }
}

