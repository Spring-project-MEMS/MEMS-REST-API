package com.fixit.web;


import com.fixit.domain.WardService;
import com.fixit.exception.InvalidEntityException;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/wards")
public class WardController {

    @Autowired
    private WardService wardService;

    @GetMapping
    public List<Ward> getAllUsers(){
        return wardService.findAll();
    }


    @PostMapping
    public ResponseEntity<Ward> addWard(@Valid @RequestBody Ward ward, BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        Ward created = wardService.add(ward);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

   @GetMapping("{id}")
    public Ward getWardById(@PathVariable Long id)
    {
        return wardService.findById(id);
    }

    @PutMapping("{id}")
    public Ward updateWard(@PathVariable Long id,@Valid @RequestBody Ward ward,BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        if(id != ward.getId())
        {
            throw new InvalidEntityException(String.format("Entity ID = '%d' is different from URL resource ID='%d'",ward.getId(),id));
        }
        return wardService.update(ward);
    }


    @DeleteMapping("{id}")
    public Ward removeWard(@PathVariable Long id)
    {
        return wardService.remove(id);
    }

}
