package com.fixit.dao;

import com.fixit.model.ResultIrm;
import com.fixit.model.User;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ResultIrmRepository extends BaseResultRepository<ResultIrm> {
    List<ResultIrm> findAllByPatient(User user);
}
