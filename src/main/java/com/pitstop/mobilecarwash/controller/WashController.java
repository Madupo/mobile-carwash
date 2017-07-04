package com.pitstop.mobilecarwash.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitstop.mobilecarwash.entity.Message;
import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.entity.Wash;
import com.pitstop.mobilecarwash.service.BookAWashService;
import com.pitstop.mobilecarwash.util.EmailUtil;
import com.pitstop.mobilecarwash.util.SecurityUtils;
import com.pitstop.mobilecarwash.util.UserUtils;
import com.pitstop.mobilecarwash.util.WashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by emmie on 2017/07/04.
 */
@Controller
@RequestMapping("/wash")
public class WashController {
    @Autowired
    private BookAWashService bookAWashService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getWashes(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                           authorization) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            logger.info("value of authorisation is " + authorization);
            return new ResponseEntity<>(bookAWashService.getAllWashes(), HttpStatus.OK);

        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    /*
    * Method to create new user and add them to DB
    * param User object
    * returns HttpResponse
    * */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity createUser(@RequestBody String json) {
        try {

            System.out.println("json is " + json);
            final JsonNode data = mapper.readTree(json);
            System.out.println("json data is "  + data);

            Wash wash;




                System.out.println("email address number doesn't exist");
                Wash  washDTO = WashUtils.parse(json, mapper, bookAWashService);


                wash = bookAWashService.bookWash(washDTO);
                EmailUtil.sendEmail(user.getEmailAddress(),"Pitstop Car Wash Registration","");
                System.out.println("done sending emails");
                return new ResponseEntity<>(user, HttpStatus.CREATED);


        } catch (RuntimeException e) {
            logger.debug(e.getLocalizedMessage());
            System.out.println("reaches RuntimeException catch " + e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());

            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (NoSuchAlgorithmException e) {
            logger.debug(e.getLocalizedMessage());
            System.out.println("reaches NoSuchAlgorithmException catch " + e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (InvalidKeySpecException e) {
            logger.debug(e.getLocalizedMessage());
            System.out.println("reaches InvalidKeySpecException catch " + e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("reaches last catch " + e.getLocalizedMessage());
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.FORBIDDEN);
        }

}
