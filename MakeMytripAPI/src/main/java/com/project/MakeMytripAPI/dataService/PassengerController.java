package com.project.MakeMytripAPI.dataService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.MakeMytripAPI.model.Passenger;
import com.project.MakeMytripAPI.repository.PassengerRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class PassengerController {

	/*private MongoOperations mongoOps;*/
	private static final String PASSENGER_COLLECTION = "Passenger";
	
	/*public PassengerDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	*/
	@Autowired
	private PassengerRepository passengerDAO;
	
	@ApiOperation(value = "Create passenger List")
	@RequestMapping(method=RequestMethod.POST, value="/passenger/create")
	public String addPassenger(@RequestBody Passenger passenger) {
		List<Passenger> passengerList=passengerDAO.findAll();
		for(Passenger passengerObj:passengerList) {
		if(passengerObj.getEmail().equals(passenger.getEmail()))
		{
			
			return "Unable to add passenger";
		}
		else
		{
			passengerDAO.save(passenger);
			return "Passenger Added sucessfully";
		}
		}
		return "";
		
	}
	
	@ApiOperation(value = "Return passenger List")
	@RequestMapping(method=RequestMethod.GET, value="/passenger/get")
	public List<Passenger> getAllPassenger() {
		return passengerDAO.findAll();
	} 
	
	@ApiOperation(value = "Return passenger List based on id")
	@RequestMapping(method=RequestMethod.GET, value="/passenger/get/{emailId}")
	public Passenger getPassenger(@PathVariable String emailId) {
		passengerDAO.findById(emailId);
		return null;
	}
	
	@ApiOperation(value = "Update passenger List based on id")
	@RequestMapping(method=RequestMethod.PUT, value="/passenger/update/{emailId}")
	public String updatePassenger(@RequestBody Passenger passenger, @PathVariable String emailId) {
		List<Passenger> passengerList=passengerDAO.findAll();
		for(Passenger passengerObj:passengerList) {
		if(passengerObj.getEmail().equals(passenger.getEmail()))
			passengerDAO.save(passenger);
			return "Passenger updated sucessfully";
		}
		
		
		return "update unsuccessful";
				
	}
	
	@ApiOperation(value = "Delete passenger List by id")
	@RequestMapping(method=RequestMethod.DELETE, value="/passenger/delete/{emailId}")
	public boolean deletePassenger(@PathVariable String emailId) {
		List<Passenger> passengerList=passengerDAO.findAll();
		for(Passenger passengerObj:passengerList) {
		if(passengerObj.getEmail().equals(emailId)) {
		passengerDAO.deleteById(emailId);
		return true;
		}
		}
		return false;
	}
}