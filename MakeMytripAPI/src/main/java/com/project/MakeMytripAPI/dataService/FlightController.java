package com.project.MakeMytripAPI.dataService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.MakeMytripAPI.model.Flight;
import com.project.MakeMytripAPI.repository.FlightRepository;
import com.project.MakeMytripAPI.support.SequenceGeneratorService;

import io.swagger.annotations.ApiOperation;

@RestController
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	SequenceGeneratorService sequenceGenerator;

	@ApiOperation(value = "Create Flight List")
	@RequestMapping(method = RequestMethod.POST, value = "flights/create")
	public String create(@RequestBody Flight flight) {
		if(flight!=null) {
			flight.setflightId((sequenceGenerator.generateSequence(Flight.SEQUENCE_NAME)));
			flightRepository.save(flight);
			return "Flight Added Successfully";
		}
		else 
			return "Unbale to add Flight";
	}

	@ApiOperation(value = "Return Flight List")
	@RequestMapping(method = RequestMethod.GET, value = "/flights/get")
	public List<Flight> FlightsList() {
		return flightRepository.findAll();
	}

	@ApiOperation(value = "Return Flight List by flightId")
	@RequestMapping(method = RequestMethod.GET, value = "/flights/get/{flightId}")
	public Flight getFlight(@PathVariable Long flightId) {
		List<Flight> flights=flightRepository.findAll();
		for (Flight list1 : flights) {

			if (list1.getflightId()==flightId) {
				return list1;
			}
		}
		return null;
	}


	@ApiOperation(value = "Update Flight List by flightId")
	@RequestMapping(method = RequestMethod.PUT, value = "/flights/update/{flightId}")
	public boolean updateFlight(@RequestBody Flight flight, @PathVariable long flightId) {

		List<Flight> flights=flightRepository.findAll();

		for (Flight Obj : flights) {
			if(Obj.getflightId()==flightId) {
				flight.setflightId(flightId);
				flightRepository.save(flight);

				return true;
			}
		}
		return false;
	}

	@ApiOperation(value = "verifies whether flights with source are present in FlightList and returns result in boolean")
	@RequestMapping(method = RequestMethod.GET,value="/flights/source/{source}")
	public boolean CheckSource(@PathVariable String source) {
		return flightRepository.existsBySource(source);
	}

	@ApiOperation(value = "verifies whether flights with destination are present in FlightList and returns result in boolean")
	@RequestMapping(method = RequestMethod.GET,value="/flights/destination/{destination}")
	public boolean CheckDestination(@PathVariable String destination) {
		return flightRepository.existsByDestination(destination);
	}

	@ApiOperation(value = "Delete Flight List by flightId")
	@RequestMapping(method = RequestMethod.DELETE, value = "/flights/delete/{flightId}")
	public String deleteFlight(@PathVariable long flightId) {
		List<Flight> flights=flightRepository.findAll();

		for (Flight flight : flights) {


			if(flight.getflightId()==flightId) {
				flightRepository.deleteById(flightId);
				return "flight deleted";
			}


		}			
		return " unable to delete flight with id "+flightId;
	}

	@ApiOperation(value = "Delete all Flight List")
	@RequestMapping(method = RequestMethod.DELETE, value = "/flights/deleteAll")
	public String deleteall() {
		List<Flight> flights=flightRepository.findAll();
		if(flights.size()>=1) {
			flightRepository.deleteAll();
			return "All flights deleted successfully";
		}
		else
			return "Unable to delete all flights";
	}

}