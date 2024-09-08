package com.RailwayManagementSystem.RailwayManagementSystem.Dto;

import com.RailwayManagementSystem.RailwayManagementSystem.Enum.TrainClass;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BookingDto {

    private List<String> seatNumbers;
    @Enumerated(EnumType.STRING)
    private TrainClass trainClass;
}
