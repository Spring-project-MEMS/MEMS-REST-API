package com.fixit.domain;

import com.fixit.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    Role findById(Long id);
    Role findByAuthority(String authority);
    Role add(Role role);
    Role update(Role role);
    Role remove(Long id);
    long count();

}
