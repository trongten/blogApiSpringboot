package com.trong10.blog.repositories;

import com.trong10.blog.enums.RoleName;
import com.trong10.blog.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(RoleName name);
}
