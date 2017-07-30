package com.pitstop.mobilecarwash.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.pitstop.mobilecarwash.entity.Message;
import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.entity.Wash;
import com.pitstop.mobilecarwash.service.BookAWashService;
import com.pitstop.mobilecarwash.service.UserService;
import com.pitstop.mobilecarwash.service.WashTimeService;
import com.pitstop.mobilecarwash.service.WashTypeService;
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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

/**
 * Created by emmie on 2017/07/04.
 */
@Controller
@RequestMapping("/wash")
public class WashController {
    @Autowired
    private BookAWashService bookAWashService;

    @Autowired
    private UserService userService;

    @Autowired
    private WashTimeService washTimeService;

    @Autowired
    private WashTypeService washTypeService;

    private static final Logger logger = LoggerFactory.getLogger(WashController.class);

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


    @RequestMapping(value = "/washTimes", method = RequestMethod.GET)
    public ResponseEntity getWashTimes(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                            authorization) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            logger.info("value of authorisation is " + authorization);
            return new ResponseEntity<>(washTimeService.getWashTimes(), HttpStatus.OK);

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
    public ResponseEntity createWash(@RequestBody String json) {
        try {

            System.out.println("json is " + json);
            final JsonNode data = mapper.readTree(json);
            System.out.println("json data is "  + data);

            Wash wash;

                System.out.println("before generating washDTO");
                System.out.println("before generating mapper " + mapper);
                System.out.println("before generating json" + json );
                System.out.println("before generating book a wash service " + bookAWashService);
                System.out.println("before generating wash type service " + washTypeService);
                System.out.println("before generating  user service " + userService);


                Wash  washDTO = WashUtils.parse(json, mapper, bookAWashService,washTypeService,userService);
                System.out.println("wash dto details is " + washDTO);

                if(washDTO!=null){
                    System.out.println("Wash dto is not null");
                    wash = bookAWashService.bookWash(washDTO);
                    System.out.println("wash after persisrance is service is " + wash);
                    return new ResponseEntity<>(new ObjectMapper().writeValueAsString(wash), HttpStatus.CREATED);
                }else{
                    System.out.println("Wash dto is null");
                }
                /*EmailUtil.sendEmail(user.getEmailAddress(),"Pitstop Car Wash Registration","");
                System.out.println("done sending emails");*/
                return null;

        } catch (RuntimeException e) {
            logger.debug(e.getLocalizedMessage());
            System.out.println("reaches RuntimeException catch " + e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());

            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("reaches last catch " + e.getLocalizedMessage());
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.FORBIDDEN);
        }


}

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity updateWash(@RequestHeader(value = "Authorization", defaultValue = "foo") String authorization, @RequestBody String json) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            Wash wash;
            final JsonNode data = mapper.readTree(json);
            Wash washDto = WashUtils.parse(json,mapper,bookAWashService,washTypeService,userService);
            wash = bookAWashService.updateWash(washDto);

            if (wash != null) {
                System.out.println("wash is " + wash.toString());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(wash), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Message.create("Error getting wash"), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getWashes(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                             authorization,  @PathVariable String id) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            long convertedId = Long.parseLong(id);
            List<Wash> washes = bookAWashService.getUsersWashes(convertedId);
            return new ResponseEntity<>(washes, HttpStatus.OK);
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
