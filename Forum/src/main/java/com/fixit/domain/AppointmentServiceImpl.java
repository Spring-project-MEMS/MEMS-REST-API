package com.fixit.domain;

import com.fixit.dao.AppointmentRepository;
import com.fixit.exception.InvalidEntityException;
import com.fixit.exception.NonexistingEntityException;
import com.fixit.model.Appointment;
import com.fixit.model.Examination;
import com.fixit.model.User;
import com.fixit.model.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private  AppointmentRepository appointmentRepository;

    @Autowired
    private  WardService wardService;

    @Autowired
    private  UserService userService;

    @Autowired
    private  ExaminationService examinationService;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow((() -> new NonexistingEntityException(
                String.format("Appointment with ID='%s' does not exist.", id))));
    }

    @Override
    public Appointment add(Appointment appointment) {
        if(!appointment.getTime().substring(3).equals("00")){
            throw new InvalidEntityException(String.format("Invalid time for appointment %s"
                    , appointment.getTime()));
        }
        if (!checkAvailability(appointment.getDate(), appointment.getTime())){
            throw new InvalidEntityException(String.format("These date %s and time %s are already booked"
                    , appointment.getDate(), appointment.getTime()));
        }
        User user = userService.findByEgn(appointment.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        appointment.setPatient(user);
        Ward ward = wardService.findByWardName(appointment.getWardName());
        appointment.setWard(ward);
        appointment.setExamination(null);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        Optional<Appointment> oldAppointment = appointmentRepository.findById(appointment.getId());
        if(!oldAppointment.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no appointment with id '%d'",appointment.getId()));
        }
        appointment.setExamination(oldAppointment.get().getExamination());
        List<Appointment> appointments = appointmentRepository.findAllByDateAndTime(appointment.getDate(), appointment.getTime());
        if (!appointments.isEmpty()){
            throw new InvalidEntityException(String.format("These date %s and time %s are already booked"
                    , appointment.getDate(), appointment.getTime()));
        }
        User user = userService.findByEgn(appointment.getEgn());
        if (!user.isPatient()){
            throw new InvalidEntityException(String.format("These egn %s don't belong to patient in the system"
                    , user.getEgn()));
        }
        appointment.setPatient(user);
        Ward ward = wardService.findByWardName(appointment.getWardName());
        appointment.setWard(ward);
        return appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public Appointment remove(Long id) {
        Optional<Appointment> oldAppointments = appointmentRepository.findById(id);
        if(!oldAppointments.isPresent())
        {
            throw new NonexistingEntityException(String.format("There is no appointments with id '%d'",id));
        }
        boolean hasExam = false;
        List<Examination> examinations = examinationService.findAllByAppointment(oldAppointments.get());
        for (int i = 0; i <examinations.size() ; i++) {
            examinationService.remove(examinations.get(i).getId());
            hasExam = true;
        }
        if(!hasExam){
            appointmentRepository.deleteById(id);
        }
        return oldAppointments.get();
    }

    @Override
    public long count() {
        return appointmentRepository.count();
    }

    @Override
    public boolean checkAvailability(String date, String time) {
        List<Appointment> appointments = appointmentRepository.findAllByDateAndTime(date, time);
        return appointments.isEmpty();
    }

    @Override
    public List<Appointment> findAllByPatient(User patient) {
        return appointmentRepository.findAllByPatient(patient);
    }

    @Override
    public List<Appointment> findAllByWard(Ward ward) {
        return appointmentRepository.findAllByWard(ward);
    }
}
