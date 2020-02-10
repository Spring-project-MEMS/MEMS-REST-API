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

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result findById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Result with ID='%d' does not exist.", id)));
    }

    @Override
    public Result remove(Long id) {
        Optional<Result> oldResult = resultRepository.findById(id);
        if(!oldResult.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no result with id '%d'",id));
        }
        resultRepository.deleteById(id);
        return oldResult.get();
    }

    @Override
    public long count() {
        return resultRepository.count();
    }
}
