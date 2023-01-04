package cac.dailutb.repositories;

import cac.dailutb.models.TypeAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeAccountRepository extends JpaRepository<TypeAccount, Integer> {

}