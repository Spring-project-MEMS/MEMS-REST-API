package com.fixit.domain;

import com.fixit.dao.ResultBloodRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.ResultBlood;
import com.fixit.model.ResultIrm;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultBloodServiceImpl implements ResultBloodService{

    @Value("${result.blood}")
    private String resultBloodType;

    @Autowired
    private ResultBloodRepository resultBloodRepository;

    @Autowired
    private  WardService wardService;

    @Autowired
    private  UserService userService;

    @Override
    public ResultBlood add(ResultBlood resultBlood) {
        if (!resultBlood.getWardName().equals(resultBloodType)){
            throw new InvalidEntityException(String.format("These result isn't of type blood"));
        }
        User user = userService.findByEgn(resultBlood.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        resultBlood.setPatient(user);
        Ward ward = wardService.findByWardName(resultBlood.getWardName());
        resultBlood.setWard(ward);
        return resultBloodRepository.save((resultBlood));
    }

    @Override
    public ResultBlood update(ResultBlood resultBlood) {
        Optional<ResultBlood> oldResultBlood = resultBloodRepository.findById(resultBlood.getId());
        if(!oldResultBlood.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no blood result with id '%d'",resultBlood.getId()));
        }
        resultBlood.setExamination(oldResultBlood.get().getExamination());
        if (!resultBlood.getWardName().equals(resultBloodType)){
            throw new InvalidEntityException(String.format("These result isn't of type blood"));
        }
        User user = userService.findByEgn(resultBlood.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        resultBlood.setPatient(user);
        Ward ward = wardService.findByWardName(resultBlood.getWardName());
        resultBlood.setWard(ward);
        return resultBloodRepository.save((resultBlood));
    }
}
