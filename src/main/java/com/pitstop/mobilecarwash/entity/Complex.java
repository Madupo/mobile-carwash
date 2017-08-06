package com.pitstop.mobilecarwash.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by emmie on 2017/06/28.
 */
@Entity
@Table(name = "complexes")
public class Complex {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String complexName;

    @Column
    private String complexLocation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="CAT")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="CAT")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Complex() {
    }

    public Complex(String complexName, String complexLocation) {
        this.complexName = complexName;
        this.complexLocation = complexLocation;
    }

    public Complex(String complexName, String complexLocation, Date created, Date modified) {
        this.complexName = complexName;
        this.complexLocation = complexLocation;
        this.created = created;
        this.modified = modified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComplexName() {
        return complexName;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }

    public String getComplexLocation() {
        return complexLocation;
    }

    public void setComplexLocation(String complexLocation) {
        this.complexLocation = complexLocation;
    }

    @Override
    public String toString() {
        return "Complex{" +
                "id=" + id +
                ", complexName='" + complexName + '\'' +
                ", complexLocation='" + complexLocation + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
