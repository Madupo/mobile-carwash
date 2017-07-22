package com.pitstop.mobilecarwash.service;



import com.pitstop.mobilecarwash.entity.Complex;

import java.util.List;

/**
 * Created by emmie on 2017/06/28.
 */
public interface ComplexService {
     Complex addComplex(Complex complex);
     List<Complex> getComplexes();
     Complex getComplex(long complex_id);
}
