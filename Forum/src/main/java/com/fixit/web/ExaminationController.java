package com.fixit.web;

import com.fixit.domain.ExaminationService;
import com.fixit.domain.UserService;
import com.fixit.domain.WardService;
import com.fixit.exception.InvalidEntityException;
import com.fixit.model.Examination;
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
@RequestMapping("api/examinations")
public class ExaminationController {

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private WardService wardService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Examination> getAllExaminations(){
        return examinationService.findAll();
    }

    @GetMapping("/wards/{wardId}")
    public List<Examination> getAllExaminationsByWard(@PathVariable Long wardId)
    {
        Ward ward = wardService.findById(wardId);
        return examinationService.findAllByWard(ward);
    }

    @GetMapping("/patients/{patientId}")
    public List<Examination> getAllExaminationsByPatient(@PathVariable Long patientId)
    {
        User user = userService.findById(patientId);
        if(!user.isPatient()){
            throw new InvalidEntityException(String.format("There is no patient with id %s", patientId));
        }
        return examinationService.findAllByPatient(user);
    }

    @GetMapping("/statuses/{status}")
    public List<Examination> getAllExaminationsByStatuses(@PathVariable String status)
    {
        return examinationService.findAllByStatus(status);
    }

    @GetMapping("/patients/{patientId}/statuses/{status}")
    public List<Examination> getAllExaminationsByPatientAndStatus(@PathVariable Long patientId, @PathVariable String status)
    {
        User user = userService.findById(patientId);
        if(!user.isPatient()){
            throw new InvalidEntityException(String.format("There is no patient with id %s", patientId));
        }
        return examinationService.findAllByStatusAndPatient(status, user);
    }

    @GetMapping("/wards/{wardId}/statuses/{status}")
    public List<Examination> getAllExaminationsByWardAndStatus(@PathVariable Long wardId, @PathVariable String status)
    {
        Ward ward = wardService.findById(wardId);
        return examinationService.findAllByStatusAndWard(status, ward);
    }

    @PostMapping
    public ResponseEntity<Examination> addExamination(@Valid @RequestBody Examination examination, BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        Examination created = examinationService.add(examination);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

    @GetMapping("{id}")
    public Examination getExaminationById(@PathVariable Long id)
    {
        return examinationService.findById(id);
    }

    @PutMapping("{id}")
    public Examination updateExamination(@PathVariable Long id,@Valid @RequestBody Examination examination,BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        if(id != examination.getId())
        {
            throw new InvalidEntityException(String.format("Entity ID = '%d' is different from URL resource ID='%d'",examination.getId(),id));
        }
        return examinationService.update(examination);
    }

    @DeleteMapping("{id}")
    public Examination removeAppointment(@PathVariable Long id)
    {
        return examinationService.remove(id);
    }
}
