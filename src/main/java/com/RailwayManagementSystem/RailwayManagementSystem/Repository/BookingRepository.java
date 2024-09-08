package com.RailwayManagementSystem.RailwayManagementSystem.Repository;

import com.RailwayManagementSystem.RailwayManagementSystem.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
}
