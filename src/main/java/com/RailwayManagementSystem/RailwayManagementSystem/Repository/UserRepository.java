package com.RailwayManagementSystem.RailwayManagementSystem.Repository;

import com.RailwayManagementSystem.RailwayManagementSystem.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo findByname(String name);
}
