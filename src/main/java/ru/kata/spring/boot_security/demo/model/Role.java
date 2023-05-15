package ru.kata.spring.boot_security.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
@Entity
@Data
@Table(name = "roles")

public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String role;
    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }
    public Role() {
    }
    public Role(String role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return getRole();
    }
    public String getRole() {
        return role;
    }
    public String withoutPrefix() {
        return role.substring(5);             // !!! добавил в начало getW... и начал видеть РОЛИ!!! метод для вывода в форме без ROLE_
    }

    @Transient
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
