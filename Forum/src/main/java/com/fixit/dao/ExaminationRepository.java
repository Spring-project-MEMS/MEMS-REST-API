package com.fixit.dao;

import com.fixit.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    List<Examination> findAllByStatus(String status);
    List<Examination> findAllByAppointment(Appointment appointment);
    List<Examination> findAllByResult(Result result);
    List<Examination> findAllByPatient(User patient);
    List<Examination> findAllByWard(Ward ward);

    List<Examination> findAllByStatusAndPatient(String status, User patient);
    List<Examination> findAllByStatusAndWard(String status, Ward ward);
}
