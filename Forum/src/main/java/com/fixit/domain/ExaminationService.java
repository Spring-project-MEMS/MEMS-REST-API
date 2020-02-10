package com.fixit.domain;

import com.fixit.model.*;

import java.util.List;

public interface ExaminationService {
    List<Examination> findAll();
    List<Examination> findAllByAppointment(Appointment appointment);
    List<Examination> findAllByResult(Result result);
    List<Examination> findAllByPatient(User patient);
    List<Examination> findAllByWard(Ward ward);
    List<Examination> findAllByStatusAndPatient(String status, User patient);
    List<Examination> findAllByStatusAndWard(String status, Ward ward);
    Examination findById(Long id);
    List<Examination> findAllByStatus(String status);
    Examination add(Examination examination);
    Examination update(Examination examination);
    Examination remove(Long id);
    long count();
}
