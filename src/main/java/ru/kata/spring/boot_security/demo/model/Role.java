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
    private String name;
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toRoleStringAll() {
        return name.contains("ROLE_ADMIN")?("ADMIN"):("USER");
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
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @ManyToMany(mappedBy = "roles")
    private List<User> user;
}
