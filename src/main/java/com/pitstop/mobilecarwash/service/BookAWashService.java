package com.pitstop.mobilecarwash.service;



import com.pitstop.mobilecarwash.entity.Wash;

import java.util.List;

/**
 * Created by emmie on 2017/06/29.
 */
public interface BookAWashService {
    //todo this must add to a database and also send an email to the database guys. a certain type of email so they can added to their excel sheet.
    Wash bookWash(Wash wash);
    Wash updateWash(Wash wash);
    Wash cancelWash(Wash wash);
    List<Wash> getAllWashes();
    Wash getOneWash(long washId);
    Wash getUsersWashes(long userId);

}
