package com.example.DeleteUserService.DeleteUserService.services;

import com.example.DeleteUserService.DeleteUserService.model.UserModel;
import com.example.DeleteUserService.DeleteUserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    @Autowired
    private UserRepository userRepository;
    public Object deleteUser(UserModel userModel) {
        userRepository.delete(userModel);
        return "User deleted successfully";
    }
}
