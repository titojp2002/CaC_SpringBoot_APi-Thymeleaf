package cac.dailutb.services;

import cac.dailutb.models.TypeAccount;
import cac.dailutb.repositories.TypeAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeAccountService {
    private final TypeAccountRepository typeAccountRepository;

    public TypeAccountService(TypeAccountRepository typeAccountRepository) {
        this.typeAccountRepository = typeAccountRepository;
    }

    public Iterable<TypeAccount> findAll(){
        return typeAccountRepository.findAll();
    }


    public TypeAccount create(TypeAccount typeAccount) {
        return typeAccountRepository.save(typeAccount);
    }


}
