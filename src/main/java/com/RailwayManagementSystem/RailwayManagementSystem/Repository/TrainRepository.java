package com.RailwayManagementSystem.RailwayManagementSystem.Repository;

import com.RailwayManagementSystem.RailwayManagementSystem.Model.Train;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train,Integer> {


    List<Train> findBySourceAndDestination(String source, String destination);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Train findTrainByTrainNo(int trainNo);



}
