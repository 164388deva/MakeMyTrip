package com.project.MakeMytripAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.MakeMytripAPI.model.Bookings;

public interface BookingsRepository extends MongoRepository<Bookings, Long>{

}
