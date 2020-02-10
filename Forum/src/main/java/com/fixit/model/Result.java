package com.fixit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fixit.constants.Constants;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "result")
@JsonIgnoreProperties({"examination"})
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = true, length = 1000)
    @Size(max = 1000, message = Constants.RESULT_DESCRIPTION_LENGTH)
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @OneToOne(mappedBy = "result")
    private Examination examination;

    private String date;

    private String time;

    @Transient
    private String wardName;
    @Transient
    private String egn;

    public Result() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
