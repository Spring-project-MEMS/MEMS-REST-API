package com.fixit.dao;

import com.fixit.model.Examination;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    List<Examination> findAllByStatus(String status);
    List<Examination> findAllByPatient(User patient);
    List<Examination> findAllByWard(Ward ward);

    List<Examination> findAllByStatusAndPatient(String status, User patient);
    List<Examination> findAllByStatusAndWard(String status, Ward ward);
}
