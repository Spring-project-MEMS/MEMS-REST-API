package com.fixit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fixit.constants.Constants;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "user")
//, "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "enabled"
@JsonIgnoreProperties({"appointments", "examinations", "results"})
@JsonView(Views.User.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Appointment.class})
    private Long id;

    private String password;

    @Size(min = 4, max = 20, message = Constants.USERNAME_LENGTH)
    @Column(unique = true)
    @JsonView({Views.Appointment.class})
    private String username;

    @NotEmpty(message = Constants.ENTER_VALID_EMAIL)
    @Email(message = Constants.ENTER_VALID_EMAIL)
    private String email;

    @Size(max = 50, message = Constants.FIRST_NAME_LENGTH)
    @Column(name = "first_name", nullable = true, length = 50)
    @JsonView({Views.Appointment.class})
    private String firstName;

    @Size(max = 50, message = Constants.LAST_NAME_LENGTH)
    @Column(name = "last_name", nullable = true, length = 50)
    @JsonView({Views.Appointment.class})
    private String lastName;

    @Size(max = 20, message = Constants.EGN_LENGTH)
    @Column(name = "egn", unique = true, nullable = true, length = 20)
    @JsonView({Views.Appointment.class})
    private String egn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Appointment> appointments;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Examination> examinations;

    @OneToMany(
            mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Result> results;

    @Transient
    private String wardName;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isEnabled;
    private boolean isCredentialsNonExpired;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    @JsonProperty
    public Ward getWard() {
        return ward;
    }

    @JsonIgnore
    public void setWard(Ward ward) {
        this.ward = ward;
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

    @Override
    @JsonProperty
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @JsonProperty
    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    @JsonProperty
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @JsonProperty
    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    @JsonProperty
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @JsonProperty
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    @JsonProperty
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @JsonProperty
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void addRole(Role role){
        if (this.authorities == null){
            this.authorities = new HashSet<>();
        }
        this.authorities.add(role);
    }

    @JsonIgnore
    public String getWardName() {
        return wardName;
    }

    @JsonProperty
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }


    public boolean hasDoctorRights(){
        Iterator<Role> it = authorities.iterator();
        while(it.hasNext()){
            if (it.next().getAuthority().equals("DOCTOR")){
                return true;
            }
        }
        return false;
    }

    @JsonIgnore
    public boolean isPatient(){
        Iterator<Role> it = authorities.iterator();
        while(it.hasNext()){
            if (it.next().getAuthority().equals("PATIENT")){
                return true;
            }
        }
        return false;
    }
}
