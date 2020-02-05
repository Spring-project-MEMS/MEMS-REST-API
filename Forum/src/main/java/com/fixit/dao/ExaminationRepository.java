package com.fixit.dao;

import com.fixit.model.Examination;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

    Page<Examination> findAllByStatus(String status, Pageable pageable);
    Page<Examination> findAllByPatient(User patient, Pageable pageable);
    Page<Examination> findAllByWard(Ward ward, Pageable pageable);

    Page<Examination> findAllByStatusAndPatient(String status, User patient, Pageable pageable);
    Page<Examination> findAllByStatusAndWard(String status, Ward ward, Pageable pageable);
}
