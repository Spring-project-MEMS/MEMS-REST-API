package com.fixit.domain;

import com.fixit.model.Ward;

import java.util.List;

public interface WardService {
    List<Ward> findAll();
    Ward findById(Long id);
    Ward findByWardName(String wardName);
    Ward add(Ward ward);
    Ward update(Ward ward);
    Ward remove(Long id);
    long count();
}
