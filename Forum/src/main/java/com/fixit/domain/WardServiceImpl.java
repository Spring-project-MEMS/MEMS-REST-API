package com.fixit.domain;

import com.fixit.dao.WardRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardServiceImpl implements WardService{

    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    @Override
    public Ward findById(Long id) {
        return wardRepository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Ward with ID='%d' does not exist.", id)));
    }

    @Override
    public Ward findByWardName(String wardName) {
        Ward ward = wardRepository.findByWardName(wardName);
        if(ward == null){
            throw new NonexistingEntityException(
                    String.format("Ward with name='%s' does not exist.", wardName));
        }
        return ward;
    }

    @Override
    public Ward add(Ward ward) {
        ward.setAppointments(null);
        ward.setResults(null);
        ward.setDoctors(null);
        ward.setExaminations(null);
        return wardRepository.save(ward);
    }

    @Override
    public Ward update(Ward ward) {
        Optional<Ward> oldWard = wardRepository.findById(ward.getId());
        if(!oldWard.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no ward with id '%d'",ward.getId()));
        }
        ward.setResults(oldWard.get().getResults());
        ward.setExaminations(oldWard.get().getExaminations());
        ward.setDoctors(oldWard.get().getDoctors());
        ward.setAppointments(oldWard.get().getAppointments());
        return wardRepository.save(ward);
    }

    @Override
    public Ward remove(Long id) {
        Optional<Ward> oldWard = wardRepository.findById(id);
        if(!oldWard.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no ward with id '%d'",id));
        }
        wardRepository.deleteById(id);
        return oldWard.get();
    }

    @Override
    public long count() {
        return wardRepository.count();
    }
}
