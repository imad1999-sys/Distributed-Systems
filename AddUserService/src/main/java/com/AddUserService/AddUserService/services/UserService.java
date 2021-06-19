package com.AddUserService.AddUserService.services;


import com.AddUserService.AddUserService.models.TransactionResponse;
import com.AddUserService.AddUserService.models.UserModel;
import com.AddUserService.AddUserService.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    @Lazy
    private RestTemplate template;

    @Autowired
    private UserRepository userRepository;

    public Object addUser(UserModel userModel) throws JsonProcessingException {
        String response = "";
        UserModel newUser = new UserModel();
        newUser.setId(userModel.getId());
        newUser.setName(userModel.getName());
        newUser.setPhone(userModel.getPhone());
        logger.info("Add-User-Service Request : "+new ObjectMapper().writeValueAsString(newUser));
        String user = template.getForObject("http://search-service/userName/search/" +userModel.getName() , String.class);
        logger.info("Add-User Service getting Response from Search-Service : "+new ObjectMapper().writeValueAsString(user));
        if(user.equals("true")){
            response = "User with name { " + userModel.getName() +" } is exist";
        }
        else{
            response = "User is not Exist and the user with name { " + userModel.getName() + " } is Added";
            userRepository.save(newUser);
        }
        return response;
    }

    public Object getFallbackAddUser(UserModel userModel){
        String response = "";
        userModel.setId(0);
        userModel.setPhone(0);
        userModel.setName("No Name");
        response = "No user";
        String res = "fallback response is : " + response + " for id: " + userModel.getId() + " for name: " + userModel.getName() + " for phone: " + userModel.getPhone() + " is slow or down ... ";
        return res;
    }

    public Object tracingServices(UserModel userModel) throws JsonProcessingException {
        String response = "";
        String res = "";
        logger.info("Add-User Service Request : "+new ObjectMapper().writeValueAsString(userModel));
        String searchUserResult = template.getForObject("http://search-service/userName/search/" +userModel.getName() , String.class);
        logger.info("Add-User Service getting Response from Search-Service : "+new ObjectMapper().writeValueAsString(searchUserResult));
        if(searchUserResult.equals("true")){
            response = "the search result is: " + searchUserResult;
            String displayUserInfoResult = template.getForObject("http://log-service/info/" + userModel.getName() , String.class);
            logger.info("Add-User Service getting Response from Log : "+new ObjectMapper().writeValueAsString(displayUserInfoResult));
            res = response + "and the user info is: " + displayUserInfoResult;
            return res;
        }
        return res;
    }

    public Object tracingSecondServices(UserModel userModel) throws JsonProcessingException {
        String response = "";
        logger.info("Add-User Service Request: " + new ObjectMapper().writeValueAsString(userModel));
        String user = template.getForObject("http://search-service/userName/search/info/" +userModel.getName() , String.class);
        logger.info("Add-User Service getting Response from Search : "+new ObjectMapper().writeValueAsString(user));
        if(user.equals("true")){
            response = "the response is: " + user;
        }
        return response;
    }

}
