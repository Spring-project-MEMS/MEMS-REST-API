package com.fixit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "examination")
@JsonIgnoreProperties({"appointment", "result"})
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private Result result;

    private String date;

    private String time;

    @Transient
    private String wardName;
    @Transient
    private String egn;
    @Transient
    private Long appointmentId;
    @Transient
    private Long resultId;

    public Examination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty
    public Ward getWard() {
        return ward;
    }

    @JsonIgnore
    public void setWard(Ward ward) {
        this.ward = ward;
    }

    @JsonProperty
    public User getPatient() {
        return patient;
    }

    @JsonIgnore
    public void setPatient(User patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @JsonIgnore
    public String getWardName() {
        return wardName;
    }

    @JsonProperty
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    @JsonIgnore
    public String getEgn() {
        return egn;
    }

    @JsonProperty
    public void setEgn(String egn) {
        this.egn = egn;
    }

    @JsonIgnore
    public Long getAppointmentId() {
        return appointmentId;
    }

    @JsonProperty
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    @JsonIgnore
    public Long getResultId() {
        return resultId;
    }

    @JsonProperty
    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }
}
