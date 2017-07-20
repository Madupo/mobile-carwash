package com.pitstop.mobilecarwash.impl;

import com.pitstop.mobilecarwash.entity.WashTime;
import com.pitstop.mobilecarwash.entity.WashType;
import com.pitstop.mobilecarwash.repository.WashTimeRepository;
import com.pitstop.mobilecarwash.service.WashTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by emmie on 2017/07/17.
 */
@Service
public class WashTimeServiceImpl implements WashTimeService {
    @Autowired
    private WashTimeRepository washTimeRepository;

    @Override
    public WashTime addWashTime(WashTime washTime) {
        return washTimeRepository.save(washTime);
    }


    @Override
    public WashTime updateWashTime(WashTime washTime) {
        if(washTimeRepository.findById(washTime.getId())!=null){
            WashTime newWashTime = washTimeRepository.findById(washTime.getId());
            newWashTime.setWashTime(washTime.getWashTime());
            return washTimeRepository.save(newWashTime);
        }
        throw new EntityNotFoundException("Wash time not found");
    }

    @Override
    public List<WashTime> getWashTimes() {
        return washTimeRepository.findAll();
    }

    @Override
    public WashTime getOneWashTime(long washTimeId) {
        WashTime washTime = washTimeRepository.findById(washTimeId);
        if(washTime !=null){
            return washTime;
        }
        else{
            return null;
        }
    }

}
