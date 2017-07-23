package com.pitstop.mobilecarwash.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitstop.mobilecarwash.entity.Complex;
import com.pitstop.mobilecarwash.entity.Role;
import com.pitstop.mobilecarwash.entity.User;
import com.pitstop.mobilecarwash.security.PasswordEncrypt;
import com.pitstop.mobilecarwash.service.ComplexService;
import com.pitstop.mobilecarwash.service.RoleService;
import com.pitstop.mobilecarwash.service.UserService;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

/**
 * Created by Emmie on 2017/04/07.
 */
public class UserUtils {
    public static User parse(final String json, ObjectMapper mapper, UserService userService, ComplexService complexService, RoleService roleService) throws IOException {

        JsonNode node = mapper.readTree(json);
        System.out.print("\nwe have\n"+node.toString());
        String email;
        Role role;
        User user = userService.getUserByEmail(node.get("emailAddress").asText());
        if(user != null) {

            if(node.has("name")){ user.setName(node.get("name").asText());}
            if(node.has("surname")){ user.setSurname(node.get("surname").asText());}
            if(node.has("emailAddress")){ user.setEmailAddress(node.get("emailAddress").asText());}
            if(node.has("cellphone")){ user.setCellphone(node.get("cellphone").asText());}
            if(node.has("complexNumber")){user.setComplexNumber(node.get("complexNumber").asText());}
           /* if(node.has("active")){ user.setActive(node.get("active").asBoolean());}*/
            /*if(node.has("complexId")){
                long complex_id = node.get("complexId").asLong();
                Complex complex = complexService.getComplex(complex_id);
                if(complex!=null){
                    user.setComplex(complex);
                }
                else{
                    throw new NullPointerException("Complex not found please check your update Details");
                }

            }*/


            if(node.has("complex")){
                try {
                    JsonNode arrNode = mapper.readTree(json).get("complex");
                    System.out.println("array node is " + arrNode);
                      long complex_id = node.get("id").asLong();
                    Complex complex = complexService.getComplex(complex_id);
                if(complex!=null){
                    user.setComplex(complex);
                }
                }catch(Exception exp){
                }
            }

            else{
                throw new NullPointerException("Please provide a complex");
            }
            user.setModified(new Date());

        }
        else {
            user = new User();
            role = new Role();
            if(node.has("emailAddress"))
            {
                email = node.get("emailAddress").asText();
                user.setEmailAddress(email);
            }else
            {
                throw new NullPointerException("Email not found please check your Details");
            }

            if(node.has("name")){
                String name = node.get("name").asText();
                user.setName(name);
            }
            if(node.has("surname")){
                String surname = node.get("surname").asText();
                user.setSurname(surname);
            }

            if(node.has("cellphone")){
                String cellphone = node.get("cellphone").asText();
                user.setCellphone(cellphone);
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

            /*if(node.has("role")){
                try {
                    JsonNode arrNode = mapper.readTree(json).get("role");
                    System.out.println("array node is " + arrNode);
                    role.setId(arrNode.get("id").asInt());
                    role.setRoleName(arrNode.get("roleName").asText());
                }catch(Exception exp){
                }
            }*/

            //set role user by default
            long userRoleId = 1;
            Role foundRole = roleService.getRole(userRoleId);
            if(foundRole!=null){
                user.setRole(foundRole);
            }
            else{
                throw new NullPointerException("Role not found in database");
            }


            if(node.has("complexNumber")){
                String complexNumber = node.get("complexNumber").asText();
                user.setComplexNumber(complexNumber);
            }

            if(node.has("password")) {
                String password = node.get("password").asText();
                user.setPassword(password);
            }



            user.setCreated(new Date());
            //default values
            user.setCreditBalance(0.00);

            user.setActive(true);
            user.setFirstTimeLoggedIn(false);
            user.setModified(new Date());
        }

        return user;
    }

    public static User resetPassword(final String json,ObjectMapper mapper,UserService userService) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        JsonNode node = mapper.readTree(json);
        System.out.print("\nwe have\n"+node.toString());
        String email;
        Role role;

        if(node.has("emailAddress")){
            User user = userService.getUserByEmail(node.get("emailAddress").asText());
            if(user!=null){
                //generate password
                String newPassword = node.get("password").asText();
                    //first set the user password

                    user.setPassword(newPassword);

                    System.out.println("new password is " + user.getPassword());

                    //from there send the whole object back to the create password
                    User newPasswordUser = SecurityUtils.createPasswordHash(user);
                    if(newPasswordUser!=null){
                        return user;
                    }


            }
            else
            {
                throw new NullPointerException("User with email " + node.get("emailAddress").asText() + " does not exist");
            }
        }
        else{
            throw new NullPointerException("No email address provided");
        }

        return null;
    }
}
