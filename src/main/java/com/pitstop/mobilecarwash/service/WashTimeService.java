package com.pitstop.mobilecarwash.service;

import com.pitstop.mobilecarwash.entity.WashTime;
import com.pitstop.mobilecarwash.entity.WashType;

import java.util.List;

/**
 * Created by emmie on 2017/07/04.
 */
public interface WashTimeService {
    WashTime addWashTime(WashTime washTime);
    WashTime updateWashTime(WashTime washTime);
    List<WashTime> getWashTimes();
    WashTime getOneWashTime(long washTimeId);
}
