package com.ducthang.demoApiAngular.repository;


import com.ducthang.demoApiAngular.model.Role;
import com.ducthang.demoApiAngular.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
