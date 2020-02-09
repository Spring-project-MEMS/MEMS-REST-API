package com.fixit.domain;

import com.fixit.model.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> findAll();
    Appointment findById(Long id);
    Appointment add(Appointment ward);
    Appointment update(Appointment ward);
    Appointment remove(Long id);
    boolean checkAvailability(String date, String time);
    long count();

}
