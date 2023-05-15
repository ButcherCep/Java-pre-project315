package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {
    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public List<Role> listByRole(List<String> email) {
        return roleRepository.listByName(email);
    }
}

