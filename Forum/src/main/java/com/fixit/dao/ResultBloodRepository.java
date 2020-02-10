package com.fixit.dao;

import com.fixit.model.ResultBlood;
import com.fixit.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ResultBloodRepository  extends BaseResultRepository<ResultBlood> {
    List<ResultBlood> findAllByPatient(User user);

}

