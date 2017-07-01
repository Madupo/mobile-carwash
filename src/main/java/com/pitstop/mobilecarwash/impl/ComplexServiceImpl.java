package com.pitstop.mobilecarwash.impl;


import com.pitstop.mobilecarwash.entity.Complex;
import com.pitstop.mobilecarwash.repository.ComplexRepository;
import com.pitstop.mobilecarwash.service.ComplexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by emmie on 2017/06/28.
 */
@Service
public class ComplexServiceImpl implements ComplexService {
    @Autowired
    ComplexRepository complexRepository;

    @Override
    public Complex addComplex(Complex complex) {
        return complexRepository.save(complex);
    }

    @Override
    public List<Complex> getComplexes() {
        return complexRepository.findAll();
    }

    @Override
    public Complex getComplex(long complex_id) {
        return complexRepository.findById(complex_id);
    }
}
