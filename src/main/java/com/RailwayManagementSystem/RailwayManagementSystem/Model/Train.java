package com.RailwayManagementSystem.RailwayManagementSystem.Model;

import com.RailwayManagementSystem.RailwayManagementSystem.Enum.TrainType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
    private int trainId;
    @Column(unique = true, nullable = false)
     private int trainNo;
     private String trainName;
     private String source;
     private String destination;
    private LocalDateTime departureTime; //It is in the format like YYYY-MM-DDTHH:MM:SS
    private int availableSeats;
    private LocalDateTime arrivalTime;//It is in the format like YYYY-MM-DDTHH:MM:SS
    @Enumerated(EnumType.STRING)
    private TrainType trainType;

}
