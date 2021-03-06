package sk.stuba.fei.uim.upb.saltedhash.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.upb.saltedhash.Entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
