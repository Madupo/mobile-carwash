package com.pitstop.mobilecarwash.impl;

import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.repository.UserRepository;
import com.pitstop.mobilecarwash.service.ForgotPasswordService;
import com.pitstop.mobilecarwash.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by emmie on 2017/07/01.
 */
@Service
public class ForgotPasswordImpl implements ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User updatePassword(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if(userRepository.findByEmailAddress(user.getEmailAddress())!=null)
        {
            User newUser = userRepository.findByEmailAddress(user.getEmailAddress());
            User newPasswordUser = SecurityUtils.createPasswordHash(newUser);

            return userRepository.save(newPasswordUser);
        }
        throw new EntityNotFoundException("User cannot be updated. Not found!");
    }
}
