package com.fixit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
@JsonIgnoreProperties({"examination"})
@JsonView(Views.Appointment.class)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @OneToOne(mappedBy = "appointment")
    private Examination examination;

    private String date;

    private String time;

    @Transient
    private String wardName;
    @Transient
    private String egn;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @JsonProperty
    public User getPatient() {
        return patient;
    }

    @JsonIgnore
    public void setPatient(User patient) {
        this.patient = patient;
    }

    @JsonProperty
    public Ward getWard() {
        return ward;
    }

    @JsonIgnore
    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
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
}
