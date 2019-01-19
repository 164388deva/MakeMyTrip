package com.project.MakeMytripAPI.model;

import java.util.Date;
import com.project.MakeMytripAPI.support.DateHandler;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Document(collection="Flight")
public class Flight {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	
	
	@Id
	private long flightId;
	private double cost;
	public Flight(long flightId, String flightName, String source, String destination, Date arrival, Date departure,double cost,int capacity) {
		super();
		
		this.flightId = flightId;
		this.flightName = flightName;
		this.source = source;
		this.destination = destination;
		this.arrival = arrival;
		this.Departure = departure;
		this.cost=cost;
		this.capacity=capacity;
	}
		public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Flight() {
		super();
	}
	private int capacity;
	private String flightName;
	private String source;
	private String destination;
	@JsonDeserialize(using =DateHandler.class)
	private Date arrival;
	@JsonDeserialize(using =DateHandler.class)
	private Date Departure;
	public long getflightId() {
		return flightId;
	}
	public void setflightId(long string) {
		this.flightId = string;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getArrival() {
		return arrival;
	}
	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}
	public Date getDeparture() {
		return Departure;
	}
	public void setDeparture(Date departure) {
		Departure = departure;
	}
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", cost=" + cost + ", flightName=" + flightName + ", source=" + source
				+ ", destination=" + destination + ", arrival=" + arrival + ", Departure=" + Departure + "]";
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	

}
