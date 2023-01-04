package cac.dailutb.controllers;

import cac.dailutb.models.Transfer;
import cac.dailutb.services.TransferService;
import cac.dailutb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
public class TransferController {


    private final TransferService transferService;
    @Autowired
    private UserService userService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    //GET REQUESTS

    //GET A LIST OF TRANSFERS NO MATTER WHO IS THE ACCOUNT
    @GetMapping("/api/transfer")
    @ResponseStatus(code = HttpStatus.OK)
    public String getTransfers(){

        return "transfers";
    }

    //GET THE TRANSFER FOR A SPECIFIC TRANSFER_ID
    @GetMapping("/api/transfer/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<Transfer> byId(@PathVariable("id") int id) {
        return transferService.find(id);
    }



    //VALIDATE AND EXECUTE A TRANSFER, THEN UPDATE SOURCE AND DESTINATION ACCOUNTS
    @PostMapping("/api/transfer")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Boolean validateAndExecuteTransfer(@RequestBody Transfer transfer) {
        return transferService.resolveTransferRequest(transfer);
    }


    @PutMapping("/api/transfer/")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Transfer update(@RequestBody Transfer transfer) {
        return transferService.update(transfer);
    }

    @PatchMapping("/api/transfer/")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Transfer change(@RequestBody Transfer transfer) {
        return transferService.change(transfer);
    }

    @DeleteMapping("/api/transfer/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Boolean delete(@PathVariable("id") int id) {
        return transferService.remove(id);
    }

}
