package com.fixit.domain;

import com.fixit.dao.RoleRepository;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Role with ID='%s' does not exist.", id)));
    }

    @Override
    public Role findByAuthority(String authority) {
        Role role = this.roleRepository.findByAuthority(authority);
        if(role == null) {
            throw new NonexistingEntityException(
                    String.format("Role with authority='%s' does not exist.", authority));
        }
        return role;
    }

    @Override
    public Role remove(Long id) {
        Optional<Role> oldRole = roleRepository.findById(id);
        if(!oldRole.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no role with id '%d'",id));
        }
        roleRepository.deleteById(id);
        return oldRole.get();
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public Role add(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role update(Role role){
        Optional<Role> oldRole = roleRepository.findById(role.getId());
        if(!oldRole.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no role with id '%d'",role.getId()));
        }
        return roleRepository.save(role);
    }
}
