package com.pitstop.mobilecarwash.impl;


import com.pitstop.mobilecarwash.entity.Role;
import com.pitstop.mobilecarwash.repository.RoleRepository;
import com.pitstop.mobilecarwash.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Emmie on 2017/04/07.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(long roleId) {
        Role role = roleRepository.findById(roleId);
        if(role!=null){
            return role;
        }else{
            return null;
        }
    }
}
