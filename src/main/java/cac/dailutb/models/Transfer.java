package cac.dailutb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transfer_id;
    private int source_account_id;
    private int destination_account_id;
    private double amount;
    private Date transferedAt = new Date();


/*
@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE})
    @JoinTable(name = "account_transfer",
            joinColumns = {@JoinColumn(name="transfer_id")},
            inverseJoinColumns = {@JoinColumn(name="account_id")}
    )
    private Set<Account> ListAccounts = new HashSet<>();

 */


    @JsonIgnore
    @ManyToMany()
    private Set<Account> ListAccounts = new HashSet<>();


    public Transfer() {}

    public Transfer(int source_account_id, int destination_account_id, double amount) {
        this.source_account_id = source_account_id;
        this.destination_account_id = destination_account_id;
        this.amount = amount;
    }

    public Integer getTransfer_id() {
        return transfer_id;
    }

    public int getSource_account_id() {
        return source_account_id;
    }

    public int getDestination_account_id() {
        return destination_account_id;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTransferedAt() {
        return transferedAt;
    }


    public void setTransfer_id(Integer transfer_id) {
        this.transfer_id = transfer_id;
    }


    public void setSource_account_id(int source_account_id) {
        this.source_account_id = source_account_id;
    }

    public void setDestination_account_id(int destination_account_id) {
        this.destination_account_id = destination_account_id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransferedAt(Date transferedAt) {
        this.transferedAt = transferedAt;
    }

    public Set<Account> getListAaccounts() {
        return ListAccounts;
    }
}
