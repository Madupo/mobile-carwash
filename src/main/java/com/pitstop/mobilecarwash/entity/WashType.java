package com.pitstop.mobilecarwash.entity;

import javax.persistence.*;

/**
 * Created by emmie on 2017/07/04.
 */
@Entity
@Table(name="washtypes")
public class WashType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String washTypeName;

    @Column
    private double basePrice;

    public WashType() {
    }

    public WashType(String washTypeName, double basePrice) {
        this.washTypeName = washTypeName;
        this.basePrice = basePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWashTypeName() {
        return washTypeName;
    }

    public void setWashTypeName(String washTypeName) {
        this.washTypeName = washTypeName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
