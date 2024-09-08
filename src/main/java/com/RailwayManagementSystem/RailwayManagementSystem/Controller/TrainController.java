package com.RailwayManagementSystem.RailwayManagementSystem.Controller;

import com.RailwayManagementSystem.RailwayManagementSystem.Dto.GeneralMsgDto;
import com.RailwayManagementSystem.RailwayManagementSystem.Exception.UserException;
import com.RailwayManagementSystem.RailwayManagementSystem.Model.Train;
import com.RailwayManagementSystem.RailwayManagementSystem.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private TrainService trainService;

         @GetMapping("/getTrainsBetweenStations")
         public ResponseEntity getTrainsBetweenStations(@RequestParam String source, @RequestParam String destination,@RequestHeader("Authorization") String token){


             try{
                 List<Train> lists=trainService.getTrainsBetweenStations(source,destination,token);
                 return new ResponseEntity<>(lists,HttpStatus.OK);
             }catch (UserException ue) {
                 return new ResponseEntity<>(new GeneralMsgDto(ue.getMessage()),HttpStatus.BAD_REQUEST);
             }

         }


}
