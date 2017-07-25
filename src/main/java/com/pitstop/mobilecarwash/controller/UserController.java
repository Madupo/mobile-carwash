package com.pitstop.mobilecarwash.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitstop.mobilecarwash.entity.Message;
import com.pitstop.mobilecarwash.entity.Result;
import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.service.ComplexService;
import com.pitstop.mobilecarwash.service.LoginService;
import com.pitstop.mobilecarwash.service.RoleService;
import com.pitstop.mobilecarwash.service.UserService;
import com.pitstop.mobilecarwash.util.EmailUtil;
import com.pitstop.mobilecarwash.util.SecurityUtils;
import com.pitstop.mobilecarwash.util.UserUtils;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Emmie on 2017/04/07.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ComplexService complexService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getUsers(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                                       authorization) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            logger.info("value of authorisation is " + authorization);
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);

        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*Method to return roles
    * return ResponseEntity containing list of roles
    */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity getRoles(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                           authorization) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            logger.info("value of authorisation is " + authorization);
            return new ResponseEntity<>(roleService.getRoles(), HttpStatus.OK);

        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /*Method to return complexes
    * return ResponseEntity containing list of complexes
    */
    @RequestMapping(value = "/complexes", method = RequestMethod.GET)
    public ResponseEntity getComplex(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                           authorization) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            logger.info("value of authorisation is " + authorization);
            return new ResponseEntity<>(complexService.getComplexes(), HttpStatus.OK);

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

            User user;

            if(userService.getUserByEmail(data.get("emailAddress").asText())!= null){
                return new ResponseEntity<>(Message.create("User with email:  " + data.get("emailAddress").asText() + " already exists."), HttpStatus.CONFLICT);
            }


            else{
                System.out.println("email address number doesn't exist");
                User userDTO = UserUtils.parse(json, mapper, userService,complexService,roleService);
                System.out.println("user dto after parsing is " + userDTO);
                logger.info("user dto after parsing is --- " + userDTO);
                user = userService.addUser(userDTO);
                EmailUtil.sendEmail(user.getEmailAddress(),"Pitstop Car Wash Registration","");
                System.out.println("done sending emails");
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(user), HttpStatus.CREATED);
            }

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

    /*
    * Method to log a user into the system
    * @param json
    * returns ResponseEntity
    * */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity userLogIn(@RequestHeader(value = "Authorization", defaultValue = "foo") String authorization, @RequestBody String json) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            final JsonNode data = mapper.readTree(json);
            final String emailAddress = data.get("emailAddress").asText();
            final String password = data.get("password").asText();

            Result result = loginService.login(emailAddress, password);

            if (result.isOk()) {
                User user = userService.getUserByEmail(emailAddress);
                System.out.println("user is " + user.toString());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(user), result.getStatus());
            } else {
                return new ResponseEntity<>(Message.create(result.getMessage()), result.getStatus());
            }
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneUser(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                              authorization,  @PathVariable String id) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            long convertedId = Long.parseLong(id);
            User user = userService.getOneUser(convertedId);
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(user), HttpStatus.OK);
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public ResponseEntity findByEmail(@RequestHeader(value = "Authorization", defaultValue = "foo") String authorization, @RequestBody String json) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            User foundUser=null;
            final JsonNode data = mapper.readTree(json);
            if(data.has("emailAddress")){
                 foundUser = userService.getUserByEmail(data.get("emailAddress").asText());
                System.out.println("found user is " + foundUser);
                if (foundUser != null) {
                    System.out.println("user is " + foundUser.toString());
                    return new ResponseEntity<>(new ObjectMapper().writeValueAsString(foundUser), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(Message.create("Error getting user"), HttpStatus.BAD_REQUEST);
                }
            }
            else{
                return new ResponseEntity<>(Message.create("No email address provided"), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestHeader(value = "Authorization", defaultValue = "foo") String authorization, @RequestBody String json) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            User user;
            final JsonNode data = mapper.readTree(json);
            User userDTO = UserUtils.parse(json,mapper,userService,complexService,roleService);
            user = userService.updateUser(userDTO);

            if (user != null) {
                System.out.println("user is " + user.toString());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Message.create("Error getting user"), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ResponseEntity forgotPassword(@RequestHeader(value = "Authorization", defaultValue = "foo") String authorization, @RequestBody String json) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            User user;
            final JsonNode data = mapper.readTree(json);
            User userDTO = UserUtils.resetPassword(json,mapper,userService);
            user = userService.updateUser(userDTO);

            if (user != null) {
                System.out.println("user is " + user.toString());
                return new ResponseEntity<>(new ObjectMapper().writeValueAsString(user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Message.create("Error getting user"), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/contactUs", method = RequestMethod.POST)
    public ResponseEntity sendContactUs(@RequestHeader(value = "Authorization", defaultValue = "foo") String authorization, @RequestBody String json) {
        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            final JsonNode data = mapper.readTree(json);
            String name="";
            String message="";
            String emailAddress="";
            String cellphoneNumber="";

            if(data.has("name")){
             name = data.get("name").asText();
            }

            if(data.has("emailAddress")){

                emailAddress = data.get("emailAddress").asText();
                System.out.println("email address is " + emailAddress);
            }
            else{
                throw new NullPointerException("Email address not provided");
            }

            if(data.has("message")){
                message = data.get("message").asText();
            }
            else
            {
                throw new NullPointerException("Message not provided");
            }

            if(data.has("cellphone")){
                cellphoneNumber = data.get("cellphone").asText();
            }
            EmailUtil.sendContactUsEmail(name,emailAddress,cellphoneNumber,message);

                return new ResponseEntity<>(Message.create("Email successfully sent"), HttpStatus.OK);

        } catch (IOException e) {
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(Message.create(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (MessagingException e) {
            return new ResponseEntity<>(Message.create("There was an error sending emails"), HttpStatus.BAD_REQUEST);
        }
    }
}


