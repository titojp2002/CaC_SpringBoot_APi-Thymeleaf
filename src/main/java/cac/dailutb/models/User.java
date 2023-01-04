package cac.dailutb.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String cuil;

    //Esto es para la conexión a la tabla accounts

    @OneToMany(mappedBy = "user")
    private Set<Account> ListAccounts = new HashSet<>();



    public User() {}

    public User(String username, String password, String first_name, String last_name, String email, String cuil) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.cuil = cuil;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(user_id, other.user_id);
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getCuil() {
        return cuil;
    }
}
