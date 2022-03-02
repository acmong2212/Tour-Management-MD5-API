package com.ducthang.demoApiAngular.service.impl;

import com.ducthang.demoApiAngular.model.Role;
import com.ducthang.demoApiAngular.model.RoleName;
import com.ducthang.demoApiAngular.repository.IRoleRepository;
import com.ducthang.demoApiAngular.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
