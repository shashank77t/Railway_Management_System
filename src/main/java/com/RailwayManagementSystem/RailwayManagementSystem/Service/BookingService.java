package com.RailwayManagementSystem.RailwayManagementSystem.Service;

import com.RailwayManagementSystem.RailwayManagementSystem.Dto.BookingDto;
import com.RailwayManagementSystem.RailwayManagementSystem.Enum.Role;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.TrainException;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.UserException;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.Booking;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.Train;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.UserInfo;
import com.RailwayManagementSystem.RailwayManagementSystem.Repository.BookingRepository;
import com.RailwayManagementSystem.RailwayManagementSystem.Repository.TrainRepository;
import com.RailwayManagementSystem.RailwayManagementSystem.Repository.UserRepository;
import com.RailwayManagementSystem.RailwayManagementSystem.Utility.JwtUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
     private BookingRepository bookingRepository;
    @Autowired
    private TrainRepository trainRepository;


    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UserRepository userRepository;
    @Transactional // Ensures the entire booking process is atomic
    public Booking bookASeatByTrainNo(BookingDto bookingDto, int trainNo, String token) throws TrainException,UserException {

        // Extract token from Bearer format
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Extract username from token
        String username = jwtUtility.getUserNameFromJwtToken(token);
        UserInfo userInfo = userRepository.findByname(username);

        if (userInfo.getRole().equals(Role.PASSENGER)) {

            // Find the train with pessimistic locking
            Train train = trainRepository.findTrainByTrainNo(trainNo);

            if (train!=null) {


                // Check if enough seats are available
                int requestedSeats = bookingDto.getSeatNumbers().size();
                if (train.getAvailableSeats() >= requestedSeats) {

                    // Reduce the available seats
                    train.setAvailableSeats(train.getAvailableSeats() - requestedSeats);

                    // Create the booking
                    Booking booking = new Booking();
                    booking.setSeatNumbers(bookingDto.getSeatNumbers());
                    booking.setTrainClass(bookingDto.getTrainClass());
                    booking.setTrain(train);
                    booking.setUser(userInfo);
                    booking.setSource(train.getSource());
                    booking.setDestination(train.getDestination());
                    booking.setArrivalTime(train.getArrivalTime());
                    booking.setDepartureTime(train.getDepartureTime());

                    // Save the train and booking
                    trainRepository.save(train);
                    return bookingRepository.save(booking);

                } else {
                    throw new RuntimeException("Not enough seats available");
                }
            } else {
                throw new TrainException("Train not found");
            }
        } else {
            throw new UserException("Unauthorized user");
        }
    }
     public Booking getBookingDetailsById(int id,String token) throws UserException{
         if (token.startsWith("Bearer ")) {
             token = token.substring(7);
         }

         // Extract username from token
         String username = jwtUtility.getUserNameFromJwtToken(token);
         UserInfo userInfo = userRepository.findByname(username);
         if (userInfo.getRole().equals(Role.PASSENGER)) {
             return bookingRepository.findById(id).orElse(null);
         }else{
             throw new UserException("Here the given customer is not a PASSENGER");
         }

     }

}
