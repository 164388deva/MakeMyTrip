package com.project.MakeMytripAPI.BookingsController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.project.MakeMytripAPI.dataService.BookingsController;
import com.project.MakeMytripAPI.model.Bookings;
import com.project.MakeMytripAPI.repository.BookingsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookingsTestController {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Mock
	private BookingsRepository BookingsRepository;

	@Mock
	private  BookingsController controllerService;

	@Mock
	private Bookings mockflight;

	@InjectMocks
	private BookingsController BookingsController;

	public Date dategiver(String date) {
		Date d=null;
		DateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");		

		try {
			d=  dateformat.parse(date);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	Date departure=dategiver("2019-01-12T18:42:00.000+0000");
	Date arrival=dategiver("2019-01-11T18:42:00.000+0000");

	@Before
	public void setUp() throws Exception{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetAllBookings() throws Exception{

		List<Bookings> bookings=new ArrayList<Bookings>(Arrays.asList(new Bookings(4l, arrival,"AShutosh@gmail.com","Airlines","Ashutosh", "Delhi", "Bengaluru", arrival, departure, 2000),
				new Bookings(4l, arrival,"AShutosh@gmail.com","Airlines","Ashutosh", "Delhi", "Bengaluru", arrival, departure, 2000)));
		when(controllerService.getAllBookings()).thenReturn(bookings);

		mockMvc.perform(get("/bookings/get").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print());


	}
	
	@Test
	public void testAddFlight() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/bookings/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(   "{\"source\": \"Mumabi\",\"destination \": \"Bangalore\",\"departureDate\": \"2019-12-07T00:00:00.000+0000\",\"arrivalDate\": \"2019-05-11T00:00:00.000+0000\",\"price\": 3000,\"flightName\": \"Airlines\",\"passengerName\": \"Ashutosh\",\"bookingDate\": \"2019-12-05T00:00:00.000+0000\"}"))
		.andExpect(jsonPath("$").value("Booking done successfully"))
		.andDo(print());
	}
	

	@Test
	public void testgetBookingsByPNR() throws Exception {
		Bookings bookings= new Bookings(5l, arrival,"AShutosh@gmail.com","Airlines","Ashutosh", "Delhi", "Bengaluru", arrival, departure, 2000);
		
		when(controllerService.getBookingsByPNR(Mockito.anyLong())).thenReturn(bookings);
		
		mockMvc.perform(get("/bookings/get/5"))
		.andExpect(status().isOk())
		.andDo(print());
			}

	/*@Test
	public void testDeleteFlight() throws Exception {
		

		 mockMvc.perform(delete("/flights/delete/6"))
		.andExpect(status().isOk())
		.andDo(print());
		
	}*/

}