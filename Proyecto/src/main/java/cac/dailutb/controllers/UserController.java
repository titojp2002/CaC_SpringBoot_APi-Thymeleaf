package cac.dailutb.controllers;

import cac.dailutb.models.User;
import cac.dailutb.models.UserDto;
import cac.dailutb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("userDto")
    public UserDto userDto(){
        return new UserDto();
    }

    // GET REQUESTS

    @GetMapping("/inicio")
    String htmlGetUsers(Model model){
        model.addAttribute("title", "This is a dynamic page" );
        return "accounts";
    }

    //GET A LIST OF ALL USERS
    @GetMapping("/api/user")
    @ResponseStatus(code = HttpStatus.OK)
    public String getUsers(Model model){
        Iterable<User> user = userService.findAll();
        model.addAttribute("title", "Lista de usuarios" );
        model.addAttribute("users", user);
        return "users";
    }

    //GET A USER FOR A SPECIFIC USER_ID
    @GetMapping("/api/user/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<User> getUserById(@PathVariable("id") int id) {
        return userService.find(id);
    }


    //RETURN A USER REGISTRATION FORM
    @GetMapping("/api/user/newuserform")
    @ResponseStatus(code = HttpStatus.OK)
    public String newUserForm(Model model){
        model.addAttribute("title", "Register a new user" );
        model.addAttribute("user", new User());
        return "registeruser";
    }

    //POST REQUESTS
    //CREATE A NEW USER
    @PostMapping("/api/user/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String create(@ModelAttribute("userDto") UserDto userDto, Model model) {
        User user = new User(userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirst_name(),
                userDto.getLast_name(),
                userDto.getEmail(),
                userDto.getCuil());

        userService.create(user);

        Iterable<User> users = userService.findAll();
        model.addAttribute("title", "Lista de usuarios" );
        model.addAttribute("users", users);
        return "users";
    }


    //PUT REQUESTS
    //UPDATE ANY USER INFORMATION
    @PutMapping("/api/user/")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public User update(@RequestBody User user) {
        return userService.update(user);
    }


    //UPDATE ANY USER INFORMATION
    @PatchMapping("/api/user/")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public User change(@RequestBody User user) {
        return userService.change(user);
    }

    //DELETE A USER
    @DeleteMapping("/api/user/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Boolean delete(@PathVariable("id") int id) {
        return userService.remove(id);
    }


}

