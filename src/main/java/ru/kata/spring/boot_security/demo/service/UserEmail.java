package ru.kata.spring.boot_security.demo.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
@Getter
@Setter
public class UserEmail extends User {
    public  String email;
    public UserEmail(String email,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email=email;
    }
}
