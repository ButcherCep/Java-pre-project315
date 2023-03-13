package ru.kata.spring.boot_security.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Entity
@Data
@Table(name = "roles")

public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }
    public Role(String name) {
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return getName();
    }
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Collection<User> user;
}
