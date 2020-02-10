package com.fixit.domain;

import com.fixit.dao.ExaminationRepository;
import com.fixit.dao.ResultBloodRepository;
import com.fixit.dao.ResultIrmRepository;
import com.fixit.dao.ResultRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ExaminationService examinationService;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public List<Result> findAllByPatient(User user) {
        return resultRepository.findAllByPatient(user);
    }

    @Override
    public List<Result> findAllByWard(Ward ward) {
        return resultRepository.findAllByWard(ward);
    }

    @Override
    public Result findById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Result with ID='%d' does not exist.", id)));
    }

    @Override
    @Transactional
    public Result remove(Long id) {
        Optional<Result> oldResult = resultRepository.findById(id);
        if(!oldResult.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no result with id '%d'",id));
        }
        List<Examination> examinations = examinationService.findAllByResult(oldResult.get());
        for (int i = 0; i <examinations.size() ; i++) {
            examinationService.remove(examinations.get(i).getId());
        }
//when examination is deleted it deletes result to
//        resultRepository.deleteById(id);
        return oldResult.get();
    }

    @Override
    public long count() {
        return resultRepository.count();
    }
}
