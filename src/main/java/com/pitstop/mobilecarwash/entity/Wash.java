package com.pitstop.mobilecarwash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Crebated by emmie on 2017/06/29.
 */
@Entity
@Table(name="wash")
public class Wash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column
    private String preferredDate;

    @Column
    private String preferredTime;

    @Column
    private int numberOfVehicles;

    @Column
    private String washStatus;

    @Column
    private String additionalInformation;

    @ManyToOne
    @JoinColumn(name = "wash_type_id")
    private WashType washType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="CAT")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="CAT")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Wash() {
    }

    public Wash(String preferredDate, String preferredTime, int numberOfVehicles, String washStatus, String additionalInformation, WashType washType, Date created, Date modified, User user) {
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
        this.numberOfVehicles = numberOfVehicles;
        this.washStatus = washStatus;
        this.additionalInformation = additionalInformation;
        this.washType = washType;
        this.created = created;
        this.modified = modified;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(String preferredDate) {
        this.preferredDate = preferredDate;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public String getWashStatus() {
        return washStatus;
    }

    public void setWashStatus(String washStatus) {
        this.washStatus = washStatus;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public WashType getWashType() {
        return washType;
    }

    public void setWashType(WashType washType) {
        this.washType = washType;
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
}
