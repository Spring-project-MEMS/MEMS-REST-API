package com.fixit.dao;

import com.fixit.model.ResultBlood;
import com.fixit.model.User;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultBloodRepository  extends BaseResultRepository<ResultBlood> {
    Set<ResultBlood> findAllByPatient(User user);

}

