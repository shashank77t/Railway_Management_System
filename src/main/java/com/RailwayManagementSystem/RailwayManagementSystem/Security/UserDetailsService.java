package com.RailwayManagementSystem.RailwayManagementSystem.Security;


import com.RailwayManagementSystem.RailwayManagementSystem.Repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find the user by email (username)
        UserInfo user1= userRepository.findByname(username);

        if(user1!=null) {

           // logger.info("the user object ##############################{}",user1);
            // Create a list of authorities (roles)
            List<GrantedAuthority> authorities = new ArrayList<>();

            // Add the user's role as a granted authority
//            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(users.getRole());
//            authorities.add(sga);

            // Create and return a UserDetails object representing the user
            // UserDetails contains user details required for authentication
            return new User(user1.getName(), user1.getPassword(), authorities) ;
        } else {
            // Throw an exception if the user is not found
            throw new BadCredentialsException("User Details not found with this username: " + username);
        }
    }
}
