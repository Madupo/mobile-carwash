package com.pitstop.mobilecarwash.impl;


import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.repository.ComplexRepository;
import com.pitstop.mobilecarwash.repository.UserRepository;
import com.pitstop.mobilecarwash.service.UserService;
import com.pitstop.mobilecarwash.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Emmie on 2017/04/07.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ComplexRepository complexRepository;

    @Override
    public User addUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User encryptedUser = SecurityUtils.createPasswordHash(user);
        return userRepository.save(encryptedUser);
    }

    @Override
    public User updateUser(User user) {
        if(userRepository.findByEmailAddress(user.getEmailAddress())!=null)
        {
            User newUser = userRepository.findByEmailAddress(user.getEmailAddress());
            newUser.setEmailAddress(user.getEmailAddress());
            newUser.setCellphone(user.getCellphone());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());

            //// TODO: 2017/06/28 add logic to update complex in update profile


            return userRepository.save(user);
        }
        throw new EntityNotFoundException("User cannot be updated. Not found!");
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deactivateUser(User user) {
        if(userRepository.findByEmailAddress(user.getEmailAddress())!=null){
            user.setActive(false);
            return user;
        }
        throw new EntityNotFoundException("User cannot be deactivated. Not found!");
    }

    @Override
    public User activateUser(User user) {
        if(userRepository.findByEmailAddress(user.getEmailAddress())!=null){
            user.setActive(true);
            return user;
        }
        throw new EntityNotFoundException("User cannot be activated. Not found!");
    }


    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmailAddress(email);
        if(user!=null){
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public User getOneUser(long id) {
        User user = userRepository.findById(id);
        if(user!=null){
            return user;
        }
        else{
            return null;
        }
    }
}
