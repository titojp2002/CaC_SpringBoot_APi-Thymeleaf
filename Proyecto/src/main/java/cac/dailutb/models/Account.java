package cac.dailutb.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_id")
    private TypeAccount type;
    private String number;
    private String cbu;
    private double balance = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "account_transfer",
            joinColumns = {@JoinColumn(name="account_id")},
            inverseJoinColumns = {@JoinColumn(name="transfer_id")}
    )
    private Set<Transfer> ListTransfer = new HashSet<>();


/*
	@ManyToMany(cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			fetch = FetchType.EAGER,
			mappedBy = "ListAccounts")
	private Set<Transfer> ListTransfer = new HashSet<>();

 */


    public Account( String number, String cbu, double balance) {
        this.number = number;
        this.cbu = cbu;
        this.balance = balance;
    }


    public Account () {}

    public int getAccount_id() {
        return account_id;
    }

    public TypeAccount getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getCbu() {
        return cbu;
    }

    public double getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public Set<Transfer> getListTransfer() {
        return ListTransfer;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public void setType(TypeAccount type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setListTransfer(Set<Transfer> listTransfer) {
        ListTransfer = listTransfer;
    }
}
