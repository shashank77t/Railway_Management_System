package com.RailwayManagementSystem.RailwayManagementSystem.Service;

import com.RailwayManagementSystem.RailwayManagementSystem.Enum.Role;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.UserException;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.Train;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.UserInfo;
import com.RailwayManagementSystem.RailwayManagementSystem.Repository.TrainRepository;
import com.RailwayManagementSystem.RailwayManagementSystem.Repository.UserRepository;
import com.RailwayManagementSystem.RailwayManagementSystem.Utility.JwtFilter;
import com.RailwayManagementSystem.RailwayManagementSystem.Utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UserRepository userRepository;

    public Train addTrain(Train train,String token) throws UserException{

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract username from token
        String username = jwtUtility.getUserNameFromJwtToken(token);
        UserInfo userInfo=userRepository.findByname(username);
        if(userInfo==null){
            throw new UserException("User does not exist");
        }
        if(userInfo.getRole().equals(Role.ADMIN))
        return trainRepository.save(train);
        else{
            throw new UserException("User is unauthorized");
        }
    }

    public List<Train> getTrainsBetweenStations(String source, String destination,String token) throws UserException{
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract username from token
        String username = jwtUtility.getUserNameFromJwtToken(token);
        UserInfo userInfo=userRepository.findByname(username);
        if(userInfo==null){
            throw new UserException("User does not exist");
        }
        if(userInfo.getRole().equals(Role.PASSENGER))
        return trainRepository.findBySourceAndDestination(source,destination);
        else
           throw new UserException("User is unauthorized");
    }
}
