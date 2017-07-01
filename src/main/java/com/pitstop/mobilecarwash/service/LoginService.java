package com.pitstop.mobilecarwash.service;


import com.pitstop.mobilecarwash.entity.Result;

/**
 * Created by Emmie on 2017/04/07.
 */
public interface LoginService {
     Result login(String username, String password);
}
