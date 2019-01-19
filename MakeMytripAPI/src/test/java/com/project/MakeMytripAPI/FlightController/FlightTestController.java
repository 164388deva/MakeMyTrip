package com.project.MakeMytripAPI.FlightController;

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

import com.project.MakeMytripAPI.dataService.FlightController;
import com.project.MakeMytripAPI.model.Flight;
import com.project.MakeMytripAPI.repository.FlightRepository;

public class FlightTestController {
	
	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringBootTest
	public class FlightControllerTest {

		private MockMvc mockMvc;
		
		@Autowired
	    private WebApplicationContext wac;
		
		@Mock
		private FlightRepository flightRepository;
		
		@Mock
		 private  FlightController controllerService;
		
		@Mock
		private Flight mockflight;
		@InjectMocks
		private FlightController flightController;
		
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
		public void testGetAllFlights() throws Exception{
		
			List<Flight> flights=new ArrayList<Flight>(Arrays.asList(new Flight(4, "Airlines", "Delhi", "Bengaluru", arrival, departure, 2000,100),
					new Flight(4, "Airlines", "Delhi", "Bengaluru", arrival, departure, 2000,100)));
			
			when(controllerService.FlightsList()).thenReturn(flights);
			
			mockMvc.perform(get("/flights/get").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andDo(print());
			
			
		}

		@Test
		public void testGetFlight() throws Exception {
			Flight flight= new Flight(4, "Airlines", "Delhi", "Bengaluru", arrival, departure, 2000,100);
			
			when(controllerService.getFlight(Mockito.anyLong())).thenReturn(flight);
			
			mockMvc.perform(get("/flights/get/4"))
			.andExpect(status().isOk())
			.andDo(print());
				}

	 @Test    
	public void testUpdateFlight() throws Exception {
			
			mockMvc.perform(MockMvcRequestBuilders.put("/flights/update/22")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content("{\"cost\": 2550,\"capacity\": 0,\"flightName\": \"Airlines\",\"source\": \"Delhi\",\"destination\": \"Bengaluru\",\"arrival\": \"2019-01-11T18:42:00.000+0000\",\"departure\": \"2019-01-12T18:42:00.000+0000\"}"))
			.andExpect(jsonPath("$").value(true))
			.andDo(print());
		}
		
		
	@Test
		public void testDeleteFlight() throws Exception {
			when(controllerService.deleteFlight(Mockito.anyLong())).thenReturn("flight deleted");

			 mockMvc.perform(delete("/flights/delete/6"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value("flight deleted"))
			.andDo(print());
			
		}
		
		@Test
		public void testAddFlight() throws Exception {
			
			
			mockMvc.perform(MockMvcRequestBuilders.post("/flights/create")
	        .contentType(MediaType.APPLICATION_JSON)
	        .content("{\"cost\": 2550,\"capacity\": 0,\"flightName\": \"Airlines\",\"source\": \"Delhi\",\"destination\": \"Bengaluru\",\"arrival\": \"2019-01-11T18:42:00.000+0000\",\"departure\": \"2019-01-12T18:42:00.000+0000\"}"))
			.andExpect(jsonPath("$").value("Flight Added Successfully"))
			.andDo(print());
		}
		@Test
		public void testCheckDestination() throws Exception{

			
			when(controllerService.CheckSource(Mockito.anyString())).thenReturn(true);
			
			mockMvc.perform(get("/flights/destination/Mumbai"))
			.andExpect(status().isOk())
			.andDo(print());	
		}
	 
		@Test
		public void testCheckSource() throws Exception{
		
			when(controllerService.CheckDestination(Mockito.anyString())).thenReturn(true);
			
			mockMvc.perform(get("/flights/source/Mumbai"))
			.andExpect(status().isOk())
			.andDo(print());	
		}
	 

		@Test
		public void testDeleteAllFlights() throws Exception {
			when(controllerService.deleteFlight(Mockito.anyLong())).thenReturn("All flights deleted successfully");

			 mockMvc.perform(delete("/flights/deleteAll"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").value("All flights deleted successfully"))
			.andDo(print());
			
		}
}
}
