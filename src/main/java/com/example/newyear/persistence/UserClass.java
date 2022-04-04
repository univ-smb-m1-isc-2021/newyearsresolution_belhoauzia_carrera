package com.example.newyear.persistence;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private String token;
    private String username;
    private String password;

    public UserClass(String username,String password){
        this.token = null;
        this.username = username;
        this.password = password;

    }

    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public UserClass() {
        //JPA
    }

    public long getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
    public void setToken() {
        this.token = UUID.randomUUID().toString();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
