package com.pitstop.mobilecarwash.repository;


import com.pitstop.mobilecarwash.entity.Complex;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Emmie on 2017/04/07.
 */
public interface ComplexRepository extends JpaRepository<Complex,Long> {
    Complex findById(long id);
}
