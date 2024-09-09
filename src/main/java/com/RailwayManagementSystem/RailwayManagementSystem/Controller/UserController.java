package com.RailwayManagementSystem.RailwayManagementSystem.Controller;

import com.RailwayManagementSystem.RailwayManagementSystem.Dto.GeneralMsgDto;
import com.RailwayManagementSystem.RailwayManagementSystem.Dto.JwtRequest;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.UserException;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.Train;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.UserInfo;
import com.RailwayManagementSystem.RailwayManagementSystem.Service.TrainService;
import com.RailwayManagementSystem.RailwayManagementSystem.Service.UserService;
import com.RailwayManagementSystem.RailwayManagementSystem.Utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired
    private TrainService trainService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/passengerSignUp")
    private ResponseEntity passengerSignUp(@RequestBody UserInfo user){
       try{
           String encodedPassword = passwordEncoder.encode(user.getPassword());
           user.setPassword(encodedPassword);

           UserInfo u=userService.passengerSignUp(user);
           return new ResponseEntity<>(u, HttpStatus.OK);
       }catch (UserException ue){
           return new ResponseEntity<>(new GeneralMsgDto(ue.getMessage()),HttpStatus.BAD_REQUEST);
       }
    }
    @PostMapping("/adminSignUp")
    private ResponseEntity adminSignUp(@RequestBody UserInfo user){
        try{

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            UserInfo u=userService.adminSignUp(user);
            return new ResponseEntity(u,HttpStatus.OK);
        }catch(UserException ue){
            return new ResponseEntity<>(new GeneralMsgDto(ue.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/addTrain")
    public ResponseEntity addTrain(@RequestBody Train train, @RequestHeader("Authorization") String token){
          logger.info("hello wordl&&&&&&&&&&&&&&{}",token);
        try {
            Train t= trainService.addTrain(train, token);
            return new ResponseEntity<>(t,HttpStatus.OK);
        }catch (UserException ue){
            return new ResponseEntity<>(new GeneralMsgDto(ue.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/adminsignin")
    public ResponseEntity<?> adminsignin(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String jwtToken = jwtUtility.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());



        return new ResponseEntity<>(new GeneralMsgDto(jwtToken),HttpStatus.OK);
    }
    @PostMapping("/passengersignin")
    public ResponseEntity passengerSignIn(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        String jwtToken = jwtUtility.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());



        return new ResponseEntity<>(new GeneralMsgDto(jwtToken),HttpStatus.OK);
    }

}
