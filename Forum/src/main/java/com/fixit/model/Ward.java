package com.fixit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ward")
@JsonIgnoreProperties({"doctors", "appointments", "examinations", "results"})
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.User.class)
    private Long id;

    @Column(name = "ward_name", nullable = false, length = 50)
    @JsonView(Views.User.class)
    private String wardName;

    @Column(name = "room_number", nullable = false, length = 20)
    @JsonView(Views.User.class)
    private String roomNumber;

    @OneToMany(
            mappedBy = "ward",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<User> doctors;

    @OneToMany(
            mappedBy = "ward",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Appointment> appointments;

    @OneToMany(
            mappedBy = "ward",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Examination> examinations;

    @OneToMany(
            mappedBy = "ward",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Result> results;

    public Ward() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Set<User> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<User> doctors) {
        this.doctors = doctors;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(Set<Examination> examinations) {
        this.examinations = examinations;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
}
