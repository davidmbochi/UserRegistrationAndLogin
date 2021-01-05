package com.david.sample.repository;

import com.david.sample.model.Role;

public interface RoleDao {
    Role findRoleByName(String theRoleName);
}
