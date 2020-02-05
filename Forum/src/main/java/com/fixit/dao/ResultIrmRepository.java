package com.fixit.dao;

import com.fixit.model.ResultIrm;
import com.fixit.model.User;
import javax.transaction.Transactional;
import java.util.Set;

@Transactional
public interface ResultIrmRepository extends BaseResultRepository<ResultIrm> {
    Set<ResultIrm> findAllByPatient(User user);
}
