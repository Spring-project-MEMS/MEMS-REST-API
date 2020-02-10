package com.fixit.dao;

import com.fixit.model.Result;
import com.fixit.model.User;
import com.fixit.model.Ward;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ResultRepository extends BaseResultRepository<Result> {
    List<Result> findAllByPatient(User user);
    List<Result> findAllByWard(Ward ward);

}
