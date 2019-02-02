package com.project.MakeMytripAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.MakeMytripAPI.model.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String>{

}
