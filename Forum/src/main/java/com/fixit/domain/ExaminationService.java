package com.fixit.domain;

import com.fixit.model.Examination;

import java.util.List;

public interface ExaminationService {
    List<Examination> findAll();
    Examination findById(Long id);
    List<Examination> findAllByStatus(String status);
    Examination add(Examination examination);
    Examination update(Examination examination);
    Examination remove(Long id);
    long count();
}
