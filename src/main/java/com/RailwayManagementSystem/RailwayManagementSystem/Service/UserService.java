package com.RailwayManagementSystem.RailwayManagementSystem.Service;

import com.RailwayManagementSystem.RailwayManagementSystem.Enum.Role;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.UserException;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.UserInfo;
import com.RailwayManagementSystem.RailwayManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInfo passengerSignUp(UserInfo user) throws UserException{
        if(user==null){
            throw new UserException("user not found");
        }
        if(user.getRole().equals(Role.PASSENGER)){
            return userRepository.save(user);
        }else{
            throw  new UserException("The given user is not passenger");
        }
    }

    public UserInfo adminSignUp(UserInfo user) throws UserException{
        if(user==null){
            throw new UserException("user not found");
        }
        if(user.getRole().equals(Role.ADMIN)){
            return userRepository.save(user);
        }else{
            throw new UserException("The given user is not admin");
        }
    }
}
