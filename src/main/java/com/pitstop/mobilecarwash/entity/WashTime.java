package com.pitstop.mobilecarwash.entity;

import javax.persistence.*;

/**
 * Created by emmie on 2017/07/17.
 */
@Entity
@Table(name="washTimes")
public class WashTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String washTime;



    public WashTime() {
    }

    public WashTime(String washTime) {
        this.washTime = washTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWashTime() {
        return washTime;
    }

    public void setWashTime(String washTime) {
        this.washTime = washTime;
    }
}
