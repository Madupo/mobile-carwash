package com.pitstop.mobilecarwash.service;

import com.pitstop.mobilecarwash.entity.WashType;

import java.util.List;

/**
 * Created by emmie on 2017/07/04.
 */
public interface WashTypeService {
    WashType addWashType(WashType washType);
    WashType updateWashType(WashType washType);
    List<WashType> getWashTypes();
    WashType getOneWashType(long washTypeId);
}
