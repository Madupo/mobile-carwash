package com.pitstop.mobilecarwash.impl;

import com.pitstop.mobilecarwash.entity.Wash;
import com.pitstop.mobilecarwash.repository.WashRepository;
import com.pitstop.mobilecarwash.service.BookAWashService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmie on 2017/07/04.
 */
@Service
public class BookAWashServiceImpl implements BookAWashService {
    @Autowired
    private WashRepository washRepository;

    @Autowired
    private BookAWashService washService;

    @Override
    public Wash bookWash(Wash wash) {
        return washRepository.save(wash);
    }

    @Override
    public Wash updateWash(Wash wash) {

            if(washRepository.findById(wash.getId())!=null)
            {
                Wash newWash = washRepository.findById(wash.getId());
                newWash.setAdditionalInformation(wash.getAdditionalInformation());
                newWash.setPreferredTime(wash.getPreferredTime());
                newWash.setPreferredDate(wash.getPreferredDate());
                newWash.setWashStatus(wash.getWashStatus());
                newWash.setWashType(wash.getWashType());
                newWash.setModified(wash.getModified());
                newWash.setNumberOfVehicles(wash.getNumberOfVehicles());
                return washRepository.save(newWash);
            }
            throw new EntityNotFoundException("User cannot be updated. Not found!");
    }

    @Override
    public Wash cancelWash(Wash wash) {
        if(washRepository.findById(wash.getId())!=null)
        {
            Wash newWash = washRepository.findById(wash.getId());
            newWash.setWashStatus(wash.getWashStatus());
            return washRepository.save(newWash);
        }
        return null;
    }

    @Override
    public List<Wash> getAllWashes() {
        return washRepository.findAll();
    }

    @Override
    public Wash getOneWash(long washId) {
        if(washRepository.findById(washId)!=null){
            return washRepository.findById(washId);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Wash> getUsersWashes(final long userId) {
        //// TODO: 2017/07/04 find a way in which to return list belonging to user
        List<Wash> allWashes = washService.getAllWashes();

        List<Wash> specificWashes =new ArrayList<>();

        for(Wash wash : allWashes) {
            final Long id = wash.getUser().getId();
            System.out.println("id is " + id);
            if(id.equals(userId))
                specificWashes.add(wash);
        }
        return specificWashes;
    }
}
