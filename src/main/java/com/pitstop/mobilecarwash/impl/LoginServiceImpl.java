package com.pitstop.mobilecarwash.impl;


import com.pitstop.mobilecarwash.entity.Result;
import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.repository.UserRepository;
import com.pitstop.mobilecarwash.security.PasswordEncrypt;
import com.pitstop.mobilecarwash.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Emmie on 2017/04/07.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Result login(String emailAddress, String password) {
            try {
                User user = userRepository.findByEmailAddress(emailAddress);

                if(user == null) return Result.failure("User not found! Please try another email address", HttpStatus.NOT_FOUND);
             /*   if(!user.isActive()) return Result.failure("Your account is deactivated,please contact Administrator", HttpStatus.FORBIDDEN);*/
                if(new PasswordEncrypt().validatePassword(password, user.getPassword()))
                    return Result.successful("User is active, password correct", HttpStatus.OK);
                else return Result.failure("Invalid password", HttpStatus.UNAUTHORIZED);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return Result.failure("Technical error", HttpStatus.BAD_REQUEST);
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
                return Result.failure("Technical error", HttpStatus.BAD_REQUEST);
            }
        }
}
