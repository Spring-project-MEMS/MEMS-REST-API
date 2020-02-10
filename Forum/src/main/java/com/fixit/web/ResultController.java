package com.fixit.web;

import com.fixit.domain.*;
import com.fixit.exception.InvalidEntityException;
import com.fixit.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private ResultBloodService resultBloodService;

    @Autowired
    private ResultIrmService resultIrmService;

    @Autowired
    private WardService wardService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Result> getAllResults(){
        return resultService.findAll();
    }

    @GetMapping("/wards/{wardId}")
    public List<Result> getAllResultsByWard(@PathVariable Long wardId)
    {
        Ward ward = wardService.findById(wardId);
        return resultService.findAllByWard(ward);
    }

    @GetMapping("/patients/{patientId}")
    public List<Result> getAllResultsByPatient(@PathVariable Long patientId)
    {
        User user = userService.findById(patientId);
        if(!user.isPatient()){
            throw new InvalidEntityException(String.format("There is no patient with id %s", patientId));
        }
        return resultService.findAllByPatient(user);
    }

    @GetMapping("{id}")
    public Result getResultById(@PathVariable Long id)
    {
        return resultService.findById(id);
    }

    @PostMapping("/blood")
    public ResponseEntity<Result> addResultBlood(@Valid @RequestBody ResultBlood result, BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }


        Result created = resultBloodService.add(result);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

    @PostMapping("/irm")
    public ResponseEntity<Result> addResultIrm(@Valid @RequestBody ResultIrm result, BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }


        Result created = resultIrmService.add(result);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

    @PutMapping("/blood/{id}")
    public ResultBlood updateResultBlood(@PathVariable Long id,@Valid @RequestBody ResultBlood resultBlood,BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        if(id != resultBlood.getId())
        {
            throw new InvalidEntityException(String.format("Entity ID = '%d' is different from URL resource ID='%d'",resultBlood.getId(),id));
        }
        return resultBloodService.update(resultBlood);
    }

    @PutMapping("/irm/{id}")
    public ResultIrm updateResultIrm(@PathVariable Long id,@Valid @RequestBody ResultIrm resultIrm,BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        if(id != resultIrm.getId())
        {
            throw new InvalidEntityException(String.format("Entity ID = '%d' is different from URL resource ID='%d'",resultIrm.getId(),id));
        }
        return resultIrmService.update(resultIrm);
    }

    @DeleteMapping("{id}")
    public Result removeResult(@PathVariable Long id)
    {
        return resultService.remove(id);
    }
}
