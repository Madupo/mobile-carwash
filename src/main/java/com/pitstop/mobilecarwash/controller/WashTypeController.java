package com.pitstop.mobilecarwash.controller;

import com.pitstop.mobilecarwash.service.WashTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by emmie on 2017/07/06.
 */
@Controller
@RequestMapping("/washTypes")
public class WashTypeController {
    @Autowired
    private WashTypeService washTypeService;



}
