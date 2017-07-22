package com.pitstop.mobilecarwash.repository;


import com.pitstop.mobilecarwash.entity.Wash;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Emmie on 2017/04/07.
 */
public interface WashRepository extends JpaRepository<Wash,Long> {
    Wash findById(long washId);
}
