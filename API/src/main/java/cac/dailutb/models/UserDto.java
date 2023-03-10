package cac.dailutb.models;

public class UserDto {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private String cuil;

    public UserDto() {
    }

    public UserDto(String username, String password, String first_name, String last_name, String email, String cuil) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.cuil = cuil;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
}
