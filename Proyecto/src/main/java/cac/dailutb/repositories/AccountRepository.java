package cac.dailutb.repositories;

import cac.dailutb.models.Account;
import cac.dailutb.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {


    List<Account> findAllByuser(User user);

    Account findBycbu(String cbu);
}
