package sk.stuba.fei.uim.upb.saltedhash.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.upb.saltedhash.Entities.Account;
import sk.stuba.fei.uim.upb.saltedhash.Repositories.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    // @Autowired annotation provides the automatic dependency injection.
    @Autowired
    AccountRepository repository;

    // Save account entity in the h2 database.
    public void save(final Account account) {
        repository.save(account);
    }

    // Get all accounts from the h2 database.
    public List<Account> getAll() {
        final List<Account> accounts = new ArrayList<>();
        repository.findAll().forEach(account -> accounts.add(account));
        return accounts;
    }

    public Optional<Account> getAccount(String username) {
        return repository.findById(username);
        };

    }

