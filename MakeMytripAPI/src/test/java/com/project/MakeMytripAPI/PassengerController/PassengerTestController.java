package com.project.MakeMytripAPI.PassengerController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.project.MakeMytripAPI.dataService.PassengerController;
import com.project.MakeMytripAPI.model.Passenger;
import com.project.MakeMytripAPI.repository.PassengerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PassengerTestController {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Mock
	private PassengerRepository passengerRepository;

	@Mock
	private PassengerController controllerService;
	/*
	 * @Mock private User user;
	 */
	@Mock
	private Passenger mockPassenger;

	@InjectMocks
	private PassengerController passengerController;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGetAllPassenger() throws Exception {

		List<Passenger> passenger = new ArrayList<Passenger>(Arrays.asList(
				new Passenger("s@gmail.com", "sneha", "sneha12"), new Passenger("a@gmail.com", "ashu", "ashu2")));

		when(controllerService.getAllPassenger()).thenReturn(passenger);

		mockMvc.perform(get("/passenger/get").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetPassenger() throws Exception {
		Passenger passenger = new Passenger("sneha@gmail.com", "sneha", "sneha1");

		when(controllerService.getPassenger(Mockito.anyString())).thenReturn(passenger);

		mockMvc.perform(get("/passenger/get/s@gmail.com")).andExpect(status().isOk()).andDo(print());
	}
	
		@Test    
	public void testUpdatePassenger() throws Exception {
			
			mockMvc.perform(MockMvcRequestBuilders.put("/passenger/update/a1@gmail.com")
	        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"mgfdsakjsa@gmail.com\",\"passengerName\":\"sneha\",\"password\":\"sneha12\"}"))
			.andExpect(jsonPath("$").value("Passenger updated sucessfully"))		
			.andDo(print());
		}
	
@Test
	public void testDeletePassenger() throws Exception {

		 mockMvc.perform(delete("/passenger/delete/a1@gmail.com"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").value(true))
		.andDo(print());
		
	}
	
@Test
	public void testAddpassenger() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/passenger/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"mgfdsakjsa@gmail.com\",\"passengerName\":\"sneha\",\"password\":\"sneha12\"}"))
		.andExpect(jsonPath("$").value("Passenger Added sucessfully"))		
		.andDo(print());
	}

}
