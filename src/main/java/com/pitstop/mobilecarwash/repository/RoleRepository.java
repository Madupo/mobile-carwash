package com.pitstop.mobilecarwash.repository;


import com.pitstop.mobilecarwash.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Emmie on 2017/04/07.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(Role role);
}
