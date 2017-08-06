package com.pitstop.mobilecarwash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Emmie on 2017/04/04.
 */
@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String surname;


    @Column(unique = true)
    private String emailAddress;

    @JsonIgnore
    @Column
    private String password;

    @Column
    private Boolean firstTimeLoggedIn;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "complex_id")
    private Complex complex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="CAT")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="CAT")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @JsonIgnore
    @Column
    private String salt;

    @Column
    private String cellphone;

    @Column
    private String complexNumber;

    @Column
    private double creditBalance;

    @Column
    private boolean active;

    public User() {
    }

    public User(String name, String surname, String emailAddress, String password, Boolean firstTimeLoggedIn, Role role, Complex complex, Date created, Date modified, String salt, String cellphone, String complexNumber, double creditBalance, boolean active) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstTimeLoggedIn = firstTimeLoggedIn;
        this.role = role;
        this.complex = complex;
        this.created = created;
        this.modified = modified;
        this.salt = salt;
        this.cellphone = cellphone;
        this.complexNumber = complexNumber;
        this.creditBalance = creditBalance;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFirstTimeLoggedIn() {
        return firstTimeLoggedIn;
    }

    public void setFirstTimeLoggedIn(Boolean firstTimeLoggedIn) {
        this.firstTimeLoggedIn = firstTimeLoggedIn;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public String getComplexNumber() {
        return complexNumber;
    }

    public void setComplexNumber(String complexNumber) {
        this.complexNumber = complexNumber;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", firstTimeLoggedIn=" + firstTimeLoggedIn +
                ", role=" + role +
                ", complex=" + complex +
                ", created=" + created +
                ", modified=" + modified +
                ", salt='" + salt + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", complexNumber='" + complexNumber + '\'' +
                ", creditBalance=" + creditBalance +
                ", active=" + active +
                '}';
    }
}
