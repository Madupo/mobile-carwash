package com.pitstop.mobilecarwash.service;




import com.pitstop.mobilecarwash.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Emmie on 2017/04/07.
 */
public interface UserService {
     User addUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;
     User updateUser(User user);
     List<User> getUsers();
     User deactivateUser(User user);
     User activateUser(User user);
     User getUserByEmail(String email);
     User getOneUser(long id);
}
