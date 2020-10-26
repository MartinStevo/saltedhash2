package sk.stuba.fei.uim.upb.saltedhash.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.upb.saltedhash.Entities.LoggedUsers;
import sk.stuba.fei.uim.upb.saltedhash.Repositories.LoggedUsersRepository;

import java.util.Optional;

@Service
public class LogService {
    // @Autowired annotation provides the automatic dependency injection.
    @Autowired
    LoggedUsersRepository logRepository;

    // Save account entity in the h2 database.
    public void save(final LoggedUsers username) {
        logRepository.save(username);
    }

    // Get all accounts from the h2 database.
    public boolean check() {
        Long online = logRepository.count();
        return !online.equals(0L);
    }

    public Long count() {
        return logRepository.count();
    }

    public Optional<LoggedUsers> getAccount(String username) {
        return logRepository.findById(username);
    };

    public void removeUser() {
        logRepository.deleteAll();
    }

}
