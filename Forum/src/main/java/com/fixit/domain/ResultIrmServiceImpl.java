package com.fixit.domain;

import com.fixit.dao.ResultIrmRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.ResultIrm;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultIrmServiceImpl implements ResultIrmService {

    @Value("${result.irm}")
    private String resultIrmType;

    @Autowired
    private ResultIrmRepository resultIrmRepository;

    @Autowired
    private  WardService wardService;

    @Autowired
    private  UserService userService;

    @Override
    public ResultIrm add(ResultIrm resultIrm) {
        if (!resultIrm.getWardName().equals(resultIrmType)){
            throw new InvalidEntityException(String.format("These result isn't of type irm"));
        }
        User user = userService.findByEgn(resultIrm.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        resultIrm.setPatient(user);
        Ward ward = wardService.findByWardName(resultIrm.getWardName());
        resultIrm.setWard(ward);
        return resultIrmRepository.save((resultIrm));
    }

    @Override
    public ResultIrm update(ResultIrm resultIrm) {
        Optional<ResultIrm> oldResultIrm = resultIrmRepository.findById(resultIrm.getId());
        if(!oldResultIrm.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no irm result with id '%d'",resultIrm.getId()));
        }
        resultIrm.setExamination(oldResultIrm.get().getExamination());
        if (!resultIrm.getWardName().equals(resultIrmType)){
            throw new InvalidEntityException(String.format("These result isn't of type irm"));
        }
        User user = userService.findByEgn(resultIrm.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        resultIrm.setPatient(user);
        Ward ward = wardService.findByWardName(resultIrm.getWardName());
        resultIrm.setWard(ward);
        return resultIrmRepository.save((resultIrm));
    }
}
