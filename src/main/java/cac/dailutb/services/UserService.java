package cac.dailutb.services;

import cac.dailutb.models.User;
import cac.dailutb.repositories.AccountRepository;
import cac.dailutb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Optional<User> find(Integer id) {
        return userRepository.findById(id);
    }
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User change(User user) {
        return userRepository.save(user);
    }

    public boolean remove(Integer id) {
        userRepository.deleteById(id);
        return true;
    }


}