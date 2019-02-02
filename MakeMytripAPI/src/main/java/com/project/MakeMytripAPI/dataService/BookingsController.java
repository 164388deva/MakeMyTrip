package com.project.MakeMytripAPI.dataService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.MakeMytripAPI.model.Bookings;
import com.project.MakeMytripAPI.model.Flight;
import com.project.MakeMytripAPI.repository.BookingsRepository;
import com.project.MakeMytripAPI.support.SequenceGeneratorService;

import io.swagger.annotations.ApiOperation;

@RestController
public class BookingsController {

private static final String BOOKINGS_COLLECTION = "Bookings";
	
	@Autowired
	private BookingsRepository bookingsDAO;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;

	@ApiOperation(value = "Create Booking list")
	@RequestMapping(method=RequestMethod.POST, value="bookings/create")
	public String addBookings(@RequestBody Bookings bookings) {
		bookings.setPnr((sequenceGenerator.generateSequence(Bookings.SEQUENCE_NAME)));
		bookingsDAO.save(bookings);	
		return "Booking done successfully";
	}

	// this should be accessed by only admin 
	@ApiOperation(value = "Return Booking list")
	@RequestMapping(method=RequestMethod.GET, value="bookings/get")
	public List<Bookings> getAllBookings() {
		return bookingsDAO.findAll();
	} 
	
// This should be accessed by passenger. where he can only get the information of his booking
	@ApiOperation(value = "Return Booking list by pnr")
	@RequestMapping(method=RequestMethod.GET, value="bookings/get/{pnr}")
	public Bookings getBookingsByPNR(@PathVariable Long pnr) {
		List<Bookings> bookings=bookingsDAO.findAll();
		for(Bookings booking:bookings) {
			if(booking.getPnr()==pnr)
				return booking;
		}
		return null;
	} 
	
	@ApiOperation(value = "Delete Booking list by pnr")
	@RequestMapping(method=RequestMethod.DELETE, value="bookings/delete/{pnr}")
	public String  deleteBookings(@PathVariable Long pnr) {
		
		List<Bookings> bookings=bookingsDAO.findAll();
		for(Bookings booking:bookings) {
			if(booking.getPnr()==pnr)
				bookingsDAO.deleteById(pnr);

			return "Deleted Successfully";
		}
		return "unable Deleted";
		
	}



}
