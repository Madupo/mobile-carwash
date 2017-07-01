package com.pitstop.mobilecarwash.service;

import com.pitstop.mobilecarwash.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by emmie on 2017/07/01.
 */
public interface ForgotPasswordService {
    User updatePassword(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;

}
