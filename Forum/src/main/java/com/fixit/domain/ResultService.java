package com.fixit.domain;

import com.fixit.model.Result;
import com.fixit.model.User;
import com.fixit.model.Ward;

import java.util.List;

public interface ResultService {
    List<Result> findAll();
    List<Result> findAllByPatient(User user);
    List<Result> findAllByWard(Ward ward);
    Result findById(Long id);
    Result remove(Long id);
    long count();
}
