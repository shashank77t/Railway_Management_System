package com.RailwayManagementSystem.RailwayManagementSystem.Model;

import com.RailwayManagementSystem.RailwayManagementSystem.Enum.TrainClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    @ElementCollection
    @CollectionTable(name = "booking_seat_numbers", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "seat_number")
    private List<String> seatNumbers;
    private String source;
    private String destination;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    @Enumerated(EnumType.STRING)
    private TrainClass trainClass;


}

