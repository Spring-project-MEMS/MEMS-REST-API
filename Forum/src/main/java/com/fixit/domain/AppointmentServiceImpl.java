package com.fixit.domain;

import com.fixit.dao.AppointmentRepository;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private  AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow((() -> new NonexistingEntityException(
                String.format("Appointment with ID='%s' does not exist.", id))));
    }

    @Override
    public Appointment add(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        Optional<Appointment> oldAppointment = appointmentRepository.findById(appointment.getId());
        if(!oldAppointment.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no appointment with id '%d'",appointment.getId()));
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment remove(Long id) {
        Optional<Appointment> oldAppointments = appointmentRepository.findById(id);
        if(!oldAppointments.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no appointments with id '%d'",id));
        }
        appointmentRepository.deleteById(id);
        return oldAppointments.get();
    }

    @Override
    public long count() {
        return appointmentRepository.count();
    }
}
