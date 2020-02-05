package com.fixit.dao;

import com.fixit.model.Appointment;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Set<Appointment> findAllByDate(String date);

    Set<Appointment> findAllByDateAndWard(String date, Ward ward);

    Page<Appointment> findAllByPatient(User patient, Pageable pageable);

    Page<Appointment> findAllByWard(Ward ward, Pageable pageable);
}
