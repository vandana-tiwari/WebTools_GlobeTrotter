package com.webToolsProj.TripOrg.POJO;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "addressTable")
public class Address {

	//@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "advertisementApartment"))
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private int zip;
	
	@Transient
	private ArrayList<String> states;

	public Address() {
		states = new ArrayList<String>();
		populateStates();
	}

	public Address(String street, String city, String state, int zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public void populateStates() {
		states.add("Albama");
		states.add("Alaska");
		states.add("Arizona");
		states.add("Arkansas");
		states.add("California");
		states.add("Colorado");
		states.add("Connecticut");
		states.add("Delaware");
		states.add("Florida");
		states.add("Georgia");
		states.add("Hawaii");
		states.add("Idaho");
		states.add("Illinois");
		states.add("Indiana");
		states.add("Iowa");
		states.add("Kansas");
		states.add("Kentucky");
		states.add("Louisiana");
		states.add("Maine");
		states.add("Maryland");
		states.add("Massachusetts");
		states.add("Michigan");
		states.add("Minnesota");
		states.add("Mississippi");
		states.add("Missouri");
		states.add("Montana");
		states.add("Nebraska");
		states.add("Nevada");
		states.add("New Hampshire");
		states.add("New Jersey");
		states.add("New Mexico");
		states.add("New York");
		states.add("North Carolina");
		states.add("North Dakota");
		states.add("Ohio");
		states.add("Oklahoma");
		states.add("Oregon");
		states.add("Pennsylvania");
		states.add("Rhode Island");
		states.add("South Carolina");
		states.add("South Dakota");
		states.add("Tennessee");
		states.add("Texas");
		states.add("Utah");
		states.add("Vermont");
		states.add("Virginia");
		states.add("Washington");
		states.add("West Virginia");
		states.add("Wisconsin");
		states.add("Wyoming");
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
