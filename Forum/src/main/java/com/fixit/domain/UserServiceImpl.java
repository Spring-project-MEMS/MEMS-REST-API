package com.fixit.domain;

import com.fixit.dao.UserRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.Role;
import com.fixit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("User with ID='%d' does not exist.", id)));
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findOneByUsername(username);
        if(user == null){
            throw new NonexistingEntityException(
                    String.format("User with username='%s' does not exist.", username));
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Wrong");
        }
        return user;
    }

    @Override
    public User add(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setExaminations(null);
        user.setResults(null);
        //TODO must change when wardService is ready. TO check if this ward exists and if role of user is DOCTOR. Otherwise throw EXC
        user.setWard(null);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        if(!oldUser.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no user with id '%d'",user.getId()));
        }
//        if (!user.getAuthorities().isEmpty() && user.getWard() != null) {
//
//        }
        user.getAuthorities().forEach((role) -> {  Role existingRole = roleService.findById(role.getId());
            if (!existingRole.getAuthority().equals(role.getAuthority()))
            {throw new InvalidEntityException(String.format("Invalid role"));} });
        user.setResults(oldUser.get().getResults());
        user.setExaminations(oldUser.get().getExaminations());
        //TODO must change when wardService is ready. TO check if this ward exists and if role of user is DOCTOR. Otherwise throw EXC
        user.setWard(oldUser.get().getWard());
        return userRepository.save(user);
    }

    @Override
    public User remove(Long id) {
        Optional<User> oldUser = userRepository.findById(id);
        if(!oldUser.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no user with id '%d'",id));
        }
        userRepository.deleteById(id);
        return oldUser.get();
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
