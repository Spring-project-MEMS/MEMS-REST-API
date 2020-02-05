package com.fixit.dao;

import com.fixit.model.Result;
import com.fixit.model.User;
import com.fixit.model.Ward;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultRepository extends BaseResultRepository<Result> {
    Set<Result> findAllByPatient(User user);
    Set<Result> findAllByWard(Ward ward);

}
