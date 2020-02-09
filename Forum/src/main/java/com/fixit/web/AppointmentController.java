package com.fixit.web;

import com.fixit.domain.AppointmentService;
import com.fixit.exception.InvalidEntityException;
import com.fixit.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.findAll();
    }

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody Appointment appointment, BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        Appointment created = appointmentService.add(appointment);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{id}").build(created.getId())).body(created);
    }

    @GetMapping("{id}")
    public Appointment getAppointmentById(@PathVariable Long id)
    {
        return appointmentService.findById(id);
    }

    @PutMapping("{id}")
    public Appointment updateAppointment(@PathVariable Long id,@Valid @RequestBody Appointment appointment,BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors()) {
            String  message = bindingResult.getFieldErrors().stream()
                    .map(err -> String.format("Invalid '%s' -> '%s': %s\n",
                            err.getField(), err.getRejectedValue(), err.getDefaultMessage()))
                    .reduce("", (acc, errStr) -> acc + errStr );
            throw new InvalidEntityException(message);
        }

        if(id != appointment.getId())
        {
            throw new InvalidEntityException(String.format("Entity ID = '%d' is different from URL resource ID='%d'",appointment.getId(),id));
        }
        return appointmentService.update(appointment);
    }

    @DeleteMapping("{id}")
    public Appointment removeAppointment(@PathVariable Long id)
    {
        return appointmentService.remove(id);
    }
}
