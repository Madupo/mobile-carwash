package com.pitstop.mobilecarwash.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitstop.mobilecarwash.entity.*;
import com.pitstop.mobilecarwash.service.BookAWashService;
import com.pitstop.mobilecarwash.service.ComplexService;
import com.pitstop.mobilecarwash.service.UserService;
import com.pitstop.mobilecarwash.service.WashTypeService;

import java.io.IOException;
import java.util.Date;

/**
 * Created by emmie on 2017/07/04.
 */
public class WashUtils {
    public static Wash parse(final String json, ObjectMapper mapper, BookAWashService washService, WashTypeService washTypeService,UserService userService) throws IOException {

        JsonNode node = mapper.readTree(json);
        System.out.print("\nwe have\n"+node.toString());

        Wash wash = washService.getOneWash(node.get("washId").asLong());
        if(wash!= null) {
            if(node.has("preferredTime")){ wash.setPreferredTime(node.get("preferredTime").asText());}
            if(node.has("preferredDate")){ wash.setPreferredTime(node.get("preferredDate").asText());}
            if(node.has("numberOfVehicles")){ wash.setPreferredTime(node.get("numberOfVehicles").asText());}
            if(node.has("washStatus")){ wash.setPreferredTime(node.get("washStatus").asText());}
            if(node.has("additionalInformation")){ wash.setPreferredTime(node.get("additionalInformation").asText());}

            if(node.has("wash_type_id")){
                long wash_type_id = node.get("wash_type_id").asLong();
                WashType washType = washTypeService.getOneWashType(wash_type_id);
                if(washType!=null){
                    wash.setWashType(washType);
                }else{
                    throw new NullPointerException("wash type not found please check your Details again");
                }
            }
            wash.setModified(new Date());

        }else{
             wash = new Wash();
            if(node.has("preferredDate"))
            {
                String preferredDate = node.get("preferredDate").asText();
                wash.setPreferredDate(preferredDate);
            }else
            {
                throw new NullPointerException("preferredDate not found please check your Details");
            }

            if(node.has("preferredTime")){
                String preferredTime = node.get("preferredTime").asText();
                wash.setPreferredTime(preferredTime);
            }

            if(node.has("numberOfVehicles")){
                int numberOfVehicles = node.get("numberOfVehicles").asInt();
                wash.setNumberOfVehicles(numberOfVehicles);
            }


            //setDefaultBookStatus
            wash.setWashStatus("booked");
            wash.setCreated(new Date());
            wash.setModified(new Date());


            if(node.has("additionalInformation")){
                String additionalInformation = node.get("additionalInformation").asText();
                wash.setAdditionalInformation(additionalInformation);
            }

            if(node.has("wash_type_id")){
                long wash_type_id = node.get("wash_type_id").asLong();
                WashType washType = washTypeService.getOneWashType(wash_type_id);
                if(washType!=null){
                    wash.setWashType(washType);
                }else{
                    throw new NullPointerException("wash type not found please check your Details");
                }
            }

            if(node.has("user_id")){
                long user_id = node.get("user_id").asLong();
                User user = userService.getOneUser(user_id);
                if(user!=null){
                    System.out.println("user is found");
                    wash.setUser(user);
                }else{
                    throw new NullPointerException("User not found please check your Details");
                }
            }

        }

        return wash;
    }
}
