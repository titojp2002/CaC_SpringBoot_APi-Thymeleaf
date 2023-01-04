package cac.dailutb.controllers;

import cac.dailutb.models.TypeAccount;
import cac.dailutb.services.TypeAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@Controller
public class TypeAccountController {

    private final TypeAccountService typeAccountService;

    public TypeAccountController(TypeAccountService typeAccountService) {
        this.typeAccountService = typeAccountService;
    }

    // GET REQUESTS

    //GET ALL TYPEACCOUNTS
    @GetMapping("/api/typeaccount")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TypeAccount> getAllTypeAccounts(){
        return (List<TypeAccount>) typeAccountService.findAll();
    }

    //POST REQUESTS

    //CREATE A NEW TYPEACCOUNT
    @PostMapping("/api/typeaccount")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TypeAccount create(@RequestBody TypeAccount typeAccount) {
        return typeAccountService.create(typeAccount);
    }

}
