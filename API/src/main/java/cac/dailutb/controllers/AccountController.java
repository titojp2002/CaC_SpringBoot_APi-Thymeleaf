package cac.dailutb.controllers;

import cac.dailutb.models.*;
import cac.dailutb.repositories.AccountRepository;
import cac.dailutb.repositories.TransferRepository;
import cac.dailutb.services.AccountService;
import cac.dailutb.services.TransferService;
import cac.dailutb.services.TypeAccountService;
import cac.dailutb.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
@Controller
public class AccountController {

    private AccountService service;
    private TypeAccountService typeAccountService;
    private UserService userService;
    private AccountRepository accountRepository;

    private TransferService transferService;

    public AccountController (AccountService service, TypeAccountService typeAccountService, UserService userService, AccountRepository accountRepository, TransferService transferService) {
        this.service = service;
        this.typeAccountService = typeAccountService;
        this.userService = userService;
        this.accountRepository = accountRepository;
        this.transferService = transferService;
    }


    @ModelAttribute("accountDto")
    public AccountDto accountDto(){
        return new AccountDto();
    }

    @ModelAttribute("delAccountDto")
    public DelAccountDto delAccountDto(){
        return new DelAccountDto();
    }




    //GET A LIST OF ACCOUNTS FOR A SPECIFIC USER_ID
    @GetMapping("api/account/user/{user_id}")
    public String getAccountsOfUser(@PathVariable("user_id") int user_id, Model model) {
        Optional<User> user = userService.find(user_id);
        if(user.isPresent()){
        return generateAccountsView(user.get(), model);
        }else{
            return ("User not found");
        }
    }


    //CREATE AND ASSIGN AN ACCOUNT TO AN USER
    @PostMapping("/api/account/user/type")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public String createAssignAccountToUserDTO(@ModelAttribute("accountDto") AccountDto accountDto, Model model){
        Account account = service.CreateAccountDto(accountDto);
//        int user_id = parseInt(accountDto.getUserId());

        return generateAccountsView(account.getUser(), model);
    }

    //GENERATE A DEPOSIT FORM
    @GetMapping("api/account/deposit/form")
    public String depositForm(@ModelAttribute("accountDto") AccountDto accountDto, Model model){
        int account_id = parseInt(accountDto.getAccountId());
        Account account = service.find(account_id).get();
        model.addAttribute("account", account);
        model.addAttribute("accountNumber", "Account Number: ");
        model.addAttribute("accountType", "Account Type: " );
        return "processdeposit";
    }


    //UPDATE BALANCE
    @PostMapping("/api/account/deposit")
    public String deposit(@ModelAttribute("accountDto") AccountDto accountDto, Model model){
        int account_id = parseInt(accountDto.getAccountId());
        Account account = service.find(account_id).get();
        account.setBalance(account.getBalance() + Double.parseDouble(accountDto.getAmount()));
        account = service.update(account);
        return generateAccountsView(account.getUser(), model);
    }

    //GENERATE A TRANSFER FORM
    @GetMapping("api/account/transfer/form")
    public String transferForm(@ModelAttribute("accountDto") AccountDto accountDto, Model model){
        int account_id = parseInt(accountDto.getAccountId());
        Account account = service.find(account_id).get();
        model.addAttribute("account", account);
        model.addAttribute("accountNumber", "Account Number: ");
        model.addAttribute("accountType", "Account Type: " );
        model.addAttribute("accountBalance", "Available: " );
        return "processtransfer";
    }

    //TRANSFER
    @PostMapping("/api/account/transfer")
    public String transfer(@ModelAttribute("accountDto") AccountDto accountDto, Model model){


        int user_id = parseInt(accountDto.getUserId());
        double transferAmount = Double.parseDouble(accountDto.getTransferAmount());
        String transferCbu = accountDto.getTransferCbu();
        int sourceAccountId = parseInt(accountDto.getAccountId());

        Account sourceAccount = accountRepository.findById(sourceAccountId).get();
        Account destinationAccount = accountRepository.findBycbu(transferCbu);
        Transfer newTransfer = new Transfer(sourceAccountId, destinationAccount.getAccount_id(), transferAmount);
        Transfer persistedTransfer = transferService.create(newTransfer);

        //debito a la source account
        sourceAccount.setBalance(sourceAccount.getBalance()-transferAmount);
        //incremento a la destination account
        destinationAccount.setBalance(destinationAccount.getBalance()+transferAmount);

        //persisto datos
        destinationAccount.getListTransfer().add(persistedTransfer);
        accountRepository.save(destinationAccount);
        sourceAccount.getListTransfer().add(persistedTransfer);
        accountRepository.save(sourceAccount);

        User user = userService.find(user_id).get();
        return generateAccountsView(user, model);
    }


    //GET A LIST OF ALL TRANSFERS
//    @GetMapping("/api/account/transfer/all")
//    public String allTransfers(Model model){
//        Iterable<Transfer> transfers = transferService.findAll();
//        return generateAllTransfersView(transfers, model);
//    }

    //GET A LIST OF TRANSFERS FOR A SPECIFIC ACCOUNT
    @GetMapping("/api/account/transfer")
    public String transfers(@ModelAttribute("AccountDto") AccountDto accountDto, Model model){
        int account_id = parseInt(accountDto.getAccountId());
        Account account = accountRepository.findById(account_id).get();
        return generateTransfersView(account, model);
    }

    //DELETE AN SPECIFIC ACCOUNT FOR A USER
//    @PostMapping("/api/account/delete")
//    public String delete(@ModelAttribute("delAccountDto") DelAccountDto delAccountDto, Model model){
//        int user_id = parseInt(delAccountDto.getUserId());
//        int account_id = parseInt(delAccountDto.getAccountId());
//        service.remove(account_id);
//
//        return generateAccountsView(user_id, model);
//    }

    @GetMapping("/api/account/delete/{account_id}/{user_id}")
    public String deleteAccount( @PathVariable("account_id") int account_id,
                                 @PathVariable("user_id") int user_id,
                                 Model model){
        service.remove(account_id);
        User user = userService.find(user_id).get();
        return generateAccountsView(user, model);
    }

    //THIS METHOD GENERATES THE ACCOUNTS.HTML VIEW, RECEIVES AN INT USER_ID AND A MODEL
    public String generateAccountsView(User user, Model model){
        Iterable<TypeAccount> typeAccountList = typeAccountService.findAll();
        model.addAttribute("title", "Cuentas del usuario: " );
        model.addAttribute("user", user);
        model.addAttribute("typeAccounts", typeAccountList);
        return "accounts";
    }


    //THIS METHOD GENERATES THE TRANSFERS.HTML VIEW, RECEIVES AN ACCOUNT OBJECT AND A MODEL
    public String generateAllTransfersView(List<Transfer> transfers, Model model){
        model.addAttribute("title", "Transferencias de la cuenta con CBU: " );
        model.addAttribute("transfers", transfers);
        return "alltransfers";
    }

    //THIS METHOD GENERATES THE TRANSFERS.HTML VIEW, RECEIVES AN ACCOUNT OBJECT AND A MODEL
    public String generateTransfersView(Account account, Model model){
        model.addAttribute("title", "Transferencias de la cuenta: ");
        model.addAttribute("account", account);
        return "transfers";
    }

}
