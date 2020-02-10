package com.fixit.domain;

import com.fixit.model.Appointment;
import com.fixit.model.User;
import com.fixit.model.Ward;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll();
    Appointment findById(Long id);
    Appointment add(Appointment ward);
    Appointment update(Appointment ward);
    Appointment remove(Long id);
    boolean checkAvailability(String date, String time);
    List<Appointment> findAllByPatient(User patient);
    List<Appointment> findAllByWard(Ward ward);
    long count();
}
