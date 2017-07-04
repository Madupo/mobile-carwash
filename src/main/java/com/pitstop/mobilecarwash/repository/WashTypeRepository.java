package com.pitstop.mobilecarwash.repository;

import com.pitstop.mobilecarwash.entity.WashType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by emmie on 2017/07/04.
 */
public interface WashTypeRepository extends JpaRepository<WashType,Long> {
    WashType findById(long id);
}
