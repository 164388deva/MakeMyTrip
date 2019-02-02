package com.project.flightDataMicroService.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.flightDataMicroService.model.Flight;
import com.project.flightDataMicroService.repository.FlightRepository;
import com.project.flightDataMicroService.support.SequenceGeneratorService;

import io.swagger.annotations.ApiOperation;


@RestController
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	SequenceGeneratorService sequenceGenerator;

	@ApiOperation(value="it will add given Flight details to List Of Flights")
	@RequestMapping(method = RequestMethod.POST, value = "/flights/create")
	public String create(@RequestBody Flight flight) {
		if(flight!=null) {
			flight.setflightId((sequenceGenerator.generateSequence(Flight.SEQUENCE_NAME)));
			flightRepository.save(flight);
			return "Flight Added Successfully";
		}
		else 
			return "Unbale to add Flight";
	}

	@ApiOperation(value="it will return list of Flights")
	@RequestMapping(method = RequestMethod.GET, value = "/flights/get")
	public List<Flight> FlightsList() {
		return flightRepository.findAll();
	}

	@ApiOperation(value="it will return flight based on flightId")
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
	@ApiOperation(value="it will update flight based on flightId")
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

	
	public boolean CheckSource(@PathVariable String source) {
		return flightRepository.existsBySource(source);
	}

	public boolean CheckDestination(@PathVariable String destination) {
		return flightRepository.existsByDestination(destination);
	}
	@ApiOperation(value="it will delete flight based on flightId")
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
	@ApiOperation(value="it will delete All flights")
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
	
	@ApiOperation(value="it will return available flight based on source and destination")
	@RequestMapping(method = RequestMethod.GET,value="/flights/source/{source}/destination/{destination}")
	public List<Flight> searchFlight(@PathVariable String source ,@PathVariable String destination) {
		List<Flight> flights=flightRepository.findAll();
		List<Flight> resultedFlights=new ArrayList<Flight>();
		for (Flight flight : flights) {
			if(CheckSource(source)&& CheckDestination(destination)) {
				resultedFlights.add(flight);
				return resultedFlights;
			}		
	}
		return resultedFlights;
}
}