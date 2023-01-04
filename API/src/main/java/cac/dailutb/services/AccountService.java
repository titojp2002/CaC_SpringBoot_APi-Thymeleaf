package cac.dailutb.services;


import cac.dailutb.models.Account;
import cac.dailutb.models.AccountDto;
import cac.dailutb.models.TypeAccount;
import cac.dailutb.models.User;
import cac.dailutb.repositories.AccountRepository;
import cac.dailutb.repositories.TypeAccountRepository;
import cac.dailutb.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Service
public class AccountService {


    private AccountRepository repo;

    private TypeAccountRepository typeAccountRepository;

    private TransferService trasnferService;

    private UserService userService;
    private UserRepository userRepository;


    public AccountService(UserService userService, AccountRepository repo, TransferService trasnferService, TypeAccountRepository typeAccountRepository, UserRepository userRepository) {
        this.userService = userService;
        this.repo = repo;
        this.trasnferService = trasnferService;
        this.typeAccountRepository = typeAccountRepository;
        this.userRepository = userRepository;
    }

    public Optional<Account> find(Integer id) {
        return repo.findById(id);
    }

    public Iterable<Account> findAll() {
        return repo.findAll();
    }

    public Account create(Account account) {
        return repo.save(account);
    }


    public Boolean createAssignAccountToUser(int user_id, int type_id) {
        Account account = new Account();
        TypeAccount typeAccount = typeAccountRepository.findById(type_id).get();
        User user = userRepository.findById(user_id).get();
        Random random = new Random();
        String cbuNumber = "";
        String accountNumber = "";
        int n;
        for (int i = 0; i < 12; i++) {
            n = random.nextInt(10);
            cbuNumber += Integer.toString(n);
        }
        for (int i = 0; i < 8; i++) {
            n = random.nextInt(10);
            accountNumber += Integer.toString(n);
        }
        account.setType(typeAccount);
        account.setUser(user);
        account.setCbu(cbuNumber);
        account.setNumber(accountNumber);
        repo.save(account);
        return true;
    }

    public Account CreateAccountDto(AccountDto accountDto) {
        Account account = new Account();
        User user = userRepository.findById(parseInt(accountDto.getUserId())).get();
        TypeAccount typeAccount = typeAccountRepository.findById(parseInt(accountDto.getAccountTypeId())).get();

        Random random = new Random();
        String cbuNumber = "";
        String accountNumber = "";
        int n;
        for (int i = 0; i < 12; i++) {
            n = random.nextInt(10);
            cbuNumber += Integer.toString(n);
        }
        for (int i = 0; i < 8; i++) {
            n = random.nextInt(10);
            accountNumber += Integer.toString(n);
        }
        account.setType(typeAccount);
        account.setUser(user);
        account.setCbu(cbuNumber);
        account.setNumber(accountNumber);
        repo.save(account);
        return account;
    }

    public List<Account> getAccountOfUser(int user_id) {
        User user = userService.find(user_id).get();
        return repo.findAllByuser(user);
    }

    public Account update(Account user) {
        return repo.save(user);
    }

    public Account change(Account user) {
        return repo.save(user);
    }

    public boolean remove(Integer id) {
        boolean check = repo.existsById(id);
        if (check) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}


