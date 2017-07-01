package com.pitstop.mobilecarwash.service;





import com.pitstop.mobilecarwash.entity.Role;

import java.util.List;

/**
 * Created by Emmie on 2017/04/07.
 */
public interface RoleService {
     Role addRole(Role role);
     List<Role> getRoles();
}
