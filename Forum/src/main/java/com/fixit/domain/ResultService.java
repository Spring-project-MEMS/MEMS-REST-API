package com.fixit.domain;

import com.fixit.model.Result;

import java.util.List;

public interface ResultService {
    List<Result> findAll();
    Result findById(Long id);
    Result add(Result result);
    Result update(Result result);
    Result remove(Long id);
    long count();
}
