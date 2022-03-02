package com.ducthang.demoApiAngular.service;

import com.ducthang.demoApiAngular.model.Role;
import com.ducthang.demoApiAngular.model.RoleName;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findByName(RoleName name);

}
