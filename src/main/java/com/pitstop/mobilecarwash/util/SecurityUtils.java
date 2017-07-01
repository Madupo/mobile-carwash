package com.pitstop.mobilecarwash.util;


import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.security.PasswordEncrypt;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Emmie on 2017/03/26.
 */
public class SecurityUtils {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
    /** app authentication */
    private final static String user = "pitstopuser:pitstoppassword";

    public static boolean authorize(String authorization) {
        String[] array = authorization.split(" ");
        if(array.length == 2) {
            String basic = new String(Base64.decodeBase64(array[1]));
            return user.equals(basic);
        }
        else return false;
    }

    public static User createPasswordHash(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Create password hash:
        PasswordEncrypt enc = new PasswordEncrypt();
        try {
            String salt = enc.getSalt();
            user.setSalt(salt);
            try {
                System.out.println("new password before encryption is " +  user.getPassword());
                String encryptedPassword = enc.generateStrongPasswordHash(user.getPassword(), user.getSalt());
                user.setPassword(encryptedPassword);
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return user;
    }

}
