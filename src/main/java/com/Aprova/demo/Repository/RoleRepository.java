package com.Aprova.demo.Repository;

import com.Aprova.demo.Entity.Metas;
import com.Aprova.demo.Entity.Role;
import com.Aprova.demo.Entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleName name);
}
