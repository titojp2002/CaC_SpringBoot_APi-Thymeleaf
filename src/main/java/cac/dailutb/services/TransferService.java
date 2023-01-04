package cac.dailutb.services;

import cac.dailutb.models.Account;
import cac.dailutb.models.Transfer;
import cac.dailutb.repositories.AccountRepository;
import cac.dailutb.repositories.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public Iterable<Transfer> findAll() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> find(Integer id) {
        return transferRepository.findById(id);
    }


    public Transfer create(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public Boolean resolveTransferRequest(Transfer transfer) {
        Account accountSource = accountRepository.findById(transfer.getSource_account_id()).get();
        Account accountDestination = accountRepository.findById(transfer.getDestination_account_id()).get();
        transfer.getListAaccounts().add(accountSource);
        transfer.getListAaccounts().add(accountDestination);
        transferRepository.save(transfer);
        return true;
    }

    public Transfer update(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public Transfer change(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public boolean remove(Integer id) {
        if (transferRepository.existsById(id)) {
            transferRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
