package com.pitstop.mobilecarwash.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitstop.mobilecarwash.service.WashTypeService;
import com.pitstop.mobilecarwash.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by emmie on 2017/07/06.
 */
@Controller
@RequestMapping("/washTypes")
public class WashTypeController {
    @Autowired
    private WashTypeService washTypeService;

    private static final Logger logger = LoggerFactory.getLogger(WashTypeController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getWashes(@RequestHeader(value ="Authorization", defaultValue ="foo")String
                                            authorization) {


        try {
            if (!SecurityUtils.authorize(authorization))
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            return new ResponseEntity<>(washTypeService.getWashTypes(), HttpStatus.OK);

        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.debug(e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
