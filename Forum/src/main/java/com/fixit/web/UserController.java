package com.fixit.web;

import com.fixit.domain.RoleService;
import com.fixit.domain.UserService;
import com.fixit.exception.InvalidEntityException;
import com.fixit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user, BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        User created = userService.add(user);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.findById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id,@Valid @RequestBody User user,BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        if(id != user.getId())
        {
            throw new InvalidEntityException(String.format("Entity ID = '%d' is different from URL resource ID='%d'",user.getId(),id));
        }
        return userService.update(user);
    }

    @DeleteMapping("{id}")
    public User removeUser(@PathVariable Long id)
    {
        return userService.remove(id);
    }
}
