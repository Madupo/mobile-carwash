package com.pitstop.mobilecarwash.repository;

import com.pitstop.mobilecarwash.entity.WashTime;
import com.pitstop.mobilecarwash.entity.WashType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by emmie on 2017/07/04.
 */
public interface WashTimeRepository extends JpaRepository<WashTime,Long> {
    WashTime findById(long id);

}
