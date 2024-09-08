package com.RailwayManagementSystem.RailwayManagementSystem.Controller;

import com.RailwayManagementSystem.RailwayManagementSystem.Dto.BookingDto;
import com.RailwayManagementSystem.RailwayManagementSystem.Dto.GeneralMsgDto;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.TrainException;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.UserException;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.Booking;
import com.RailwayManagementSystem.RailwayManagementSystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookASeatByTrain")
    private ResponseEntity bookASeatByTrain(@RequestBody BookingDto bookingDto, @RequestParam int trainNo, @RequestHeader("Authorization") String token) {

        try {
            Booking b = bookingService.bookASeatByTrainNo(bookingDto, trainNo, token);
            return new ResponseEntity<>(b, HttpStatus.OK);
        }catch (UserException ue){
            return new ResponseEntity<>(new GeneralMsgDto(ue.getMessage()),HttpStatus.BAD_REQUEST);
        }catch (TrainException te){
            return new ResponseEntity<>(new GeneralMsgDto(te.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getDetailsByBookingId")
    private ResponseEntity getDetailsByBookingId(@RequestParam int bookingId,@RequestHeader("Authorization") String token) {

        try{
            Booking b=bookingService.getBookingDetailsById(bookingId,token);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }catch (UserException ue){
            return new ResponseEntity<>(new GeneralMsgDto(ue.getMessage()),HttpStatus.BAD_REQUEST);
        }

    }
}

