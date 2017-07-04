package com.pitstop.mobilecarwash.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitstop.mobilecarwash.entity.*;
import com.pitstop.mobilecarwash.service.BookAWashService;
import com.pitstop.mobilecarwash.service.ComplexService;
import com.pitstop.mobilecarwash.service.UserService;

import java.io.IOException;
import java.util.Date;

/**
 * Created by emmie on 2017/07/04.
 */
public class WashUtils {
    public static Wash parse(final String json, ObjectMapper mapper, BookAWashService washService) throws IOException {

        JsonNode node = mapper.readTree(json);
        System.out.print("\nwe have\n"+node.toString());
        String email;
        Role role;
        //Wash wash = washService.getOneWash(node.get("id").asText());


            Wash wash = new Wash();
            role = new Role();
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
                int numberOfVehicles = node.get("surname").asInt();
                wash.setNumberOfVehicles(numberOfVehicles);
            }


            //setDefaultBookStatus
            wash.setWashStatus("booked");


            if(node.has("additionalInformation")){
                String additionalInformation = node.get("additionalInformation").asText();
                wash.setAdditionalInformation(additionalInformation);
            }

            if(node.has("wash_type_id")){
                int wash_type_id = node.get("additionalInformation").asInt();
                WashType washType = washService.updateWash()
                if()


            }



            //// TODO: 2017/06/30 add complex information
            if(node.has("complexId")){
                long complex_id = node.get("complexId").asLong();
                Complex complex = complexService.getComplex(complex_id);
                if(complex!=null){
                    user.setComplex(complex);
                }
                else{
                    throw new NullPointerException("Complex not found please check your Details");
                }

            }
            else{
                throw new NullPointerException("Please provide a complex");
            }

            if(node.has("role")){
                try {
                    JsonNode arrNode = mapper.readTree(json).get("role");
                    System.out.println("array node is " + arrNode);
                    role.setId(arrNode.get("id").asInt());
                    role.setRoleName(arrNode.get("roleName").asText());
                }catch(Exception exp){
                }
            }

            if(node.has("complexNumber")){
                String complexNumber = node.get("complexNumber").asText();
                user.setComplexNumber(complexNumber);
            }

            if(node.has("password")) {
                String password = node.get("password").asText();
                user.setPassword(password);
            }

            user.setRole(role);



        //default values
        user.setCreditBalance(0.00);
        user.setCreated(new Date());
        user.setActive(true);
        user.setFirstTimeLoggedIn(false);
        user.setModified(new Date());
        return user;
    }
}
