package com.fixit.web;

import com.fixit.domain.RoleService;
import com.fixit.exception.InvalidEntityException;
import com.fixit.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles(){
        return roleService.findAll();
    }

    @GetMapping("{id}")
    public Role getRoleById(@PathVariable("id") Long id) {
        return roleService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody Role role)
    {
        Role created = roleService.add(role);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

    @PutMapping("{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        if(id != role.getId()) {
            throw new InvalidEntityException(
                    String.format("Entity ID='%d' is different from URL resource ID='%d'", role.getId(), id));
        }
        return roleService.update(role);
    }

    @DeleteMapping("{id}")
    public Role removeRole(@PathVariable Long id) {
        return roleService.remove(id);
    }
}
