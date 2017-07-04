package com.pitstop.mobilecarwash.impl;

import com.pitstop.mobilecarwash.entity.WashType;
import com.pitstop.mobilecarwash.repository.WashTypeRepository;
import com.pitstop.mobilecarwash.service.WashTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by emmie on 2017/07/04.
 */
@Service
public class WashTypeServiceImpl implements WashTypeService {

    @Autowired
    private WashTypeRepository washTypeRepository;

    @Override
    public WashType addWashType(WashType washType) {
        return washTypeRepository.save(washType);
    }

    @Override
    public WashType updateWashType(WashType washType) {
        if(washTypeRepository.findById(washType.getId())!=null){
            WashType newWashType = washTypeRepository.findById(washType.getId());
            newWashType.setBasePrice(washType.getBasePrice());
            newWashType.setWashTypeName(washType.getWashTypeName());
            return washTypeRepository.save(newWashType);
        }
       throw new EntityNotFoundException("Wash type not found");
    }

    @Override
    public List<WashType> getWashTypes() {
        return washTypeRepository.findAll();
    }

    @Override
    public WashType getOneWashType(long washTypeId) {
        WashType washType = washTypeRepository.findById(washTypeId);
        if(washType !=null){
            return washType;
        }
        else{
            return null;
        }
    }
}
