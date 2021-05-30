package com.AddUserService.AddUserService.services;


import com.AddUserService.AddUserService.models.UserModel;
import com.AddUserService.AddUserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Object addUser(UserModel userModel){
        UserModel newUser = new UserModel();
        newUser.setName(userModel.getName());
        newUser.setPhone(userModel.getPhone());
        userRepository.save(newUser);
        return newUser;
    }
}
