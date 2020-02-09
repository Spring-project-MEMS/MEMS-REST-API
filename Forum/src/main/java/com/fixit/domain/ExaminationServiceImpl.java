package com.fixit.domain;

import com.fixit.dao.ExaminationRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.Appointment;
import com.fixit.model.Examination;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    @Value("${examination.status.open}")
    private String OPEN;

    @Value("${examination.status.processed}")
    private String PROCESSED;

    @Value("${examination.status.pending}")
    private String PENDING;

    @Value("${examination.status.closed}")
    private String CLOSED;

    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private  WardService wardService;

    @Autowired
    private  UserService userService;

    @Autowired
    private AppointmentService appointmentService;


    @Override
    public List<Examination> findAll() {
        return examinationRepository.findAll();
    }

    @Override
    public Examination findById(Long id) {
        return examinationRepository.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Examination with ID='%d' does not exist.", id)));
    }

    @Override
    public List<Examination> findAllByStatus(String status) {
        return examinationRepository.findAllByStatus(status);
    }

    @Override
    public Examination add(Examination examination) {
        User user = userService.findByEgn(examination.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        examination.setPatient(user);
        Ward ward = wardService.findByWardName(examination.getWardName());
        examination.setWard(ward);
        examination.setStatus("open");
        examination.setResult(null);
        if(examination.getAppointmentId() != null){
            Appointment appointment = appointmentService.findById(examination.getAppointmentId());
            if (!appointment.getDate().equals(examination.getDate()) || !appointment.getTime().equals(examination.getTime())){
                throw new InvalidEntityException(String.format("Invalid date %s and time %s not match date %s and time %s of appointment"
                        , examination.getDate(), examination.getTime(), appointment.getDate(), appointment.getTime()));
            }
            examination.setAppointment(appointment);
        }
        else{
            if(!examination.getTime().substring(3).equals("00")){
                throw new InvalidEntityException(String.format("Invalid time for examination %s"
                        , examination.getTime()));
            }
            if (!appointmentService.checkAvailability(examination.getDate(), examination.getTime())){
                throw new InvalidEntityException(String.format("These date %s and time %s are already booked"
                        , examination.getDate(), examination.getTime()));
            }
        }
        return examinationRepository.save(examination);
    }

    @Override
    public Examination update(Examination examination) {
        String status = examination.getStatus();
        if(!status.equals(OPEN) && !status.equals(PROCESSED) && !status.equals(PENDING) && !status.equals(CLOSED)) {
            throw new InvalidEntityException(String.format("Invalid status %s", status));
        }
        User user = userService.findByEgn(examination.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        examination.setPatient(user);
        Ward ward = wardService.findByWardName(examination.getWardName());
        examination.setWard(ward);
        if(examination.getAppointmentId() != null){
            Appointment appointment = appointmentService.findById(examination.getAppointmentId());
            if (!appointment.getDate().equals(examination.getDate()) || !appointment.getTime().equals(examination.getTime())){
                throw new InvalidEntityException(String.format("Invalid date %s and time %s not match date %s and time %s of appointment"
                        , examination.getDate(), examination.getTime(), appointment.getDate(), appointment.getTime()));
            }
            examination.setAppointment(appointment);
        }
        //TODO result logic set result if status is closed
        return examinationRepository.save(examination);
    }

    @Override
    public Examination remove(Long id) {
        Optional<Examination> oldExamination = examinationRepository.findById(id);
        if(!oldExamination.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no examination with id '%d'",id));
        }
        examinationRepository.deleteById(id);
        return oldExamination.get();
    }

    @Override
    public long count() {
        return examinationRepository.count();
    }
}
