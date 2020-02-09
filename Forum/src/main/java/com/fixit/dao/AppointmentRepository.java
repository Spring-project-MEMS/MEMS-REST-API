package com.fixit.dao;

import com.fixit.model.Appointment;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDate(String date);

    List<Appointment> findAllByDateAndWard(String date, Ward ward);

    List<Appointment> findAllByPatient(User patient);

    List<Appointment> findAllByWard(Ward ward);

    List<Appointment> findAllByDateAndTime(String date, String time);
}
