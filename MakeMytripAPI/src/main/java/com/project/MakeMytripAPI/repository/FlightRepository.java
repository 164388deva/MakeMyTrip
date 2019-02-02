package com.project.MakeMytripAPI.repository;






import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.MakeMytripAPI.model.Flight;

public interface FlightRepository extends MongoRepository<Flight, Long> {

	//void insert(Optional<Flight> flightObj);

	
	public boolean existsBySource(String source);
	public boolean existsByDestination(String destination);
	
	//void deleteById(String id);
	
	//Flight findByName(String flightName);
	//Flight findById(long flightId);
	
}