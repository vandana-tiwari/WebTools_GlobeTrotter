package com.webToolsProj.TripOrg.POJO;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookingTable")
public class Booking {
	@Id
	@GeneratedValue
	@Column(name="bookingID")
	private long bookingID;
	
	@Column(name="bookingDate")
	private Date bookingDate;
	
	@Column(name="checkInDate")
	private Date checkInDate;
	
	@Column(name="checkOutDate")
	private Date checkOutDate;
	
	@Column(name="rooms")
	private int rooms;
	
	@Column(name="occupants")
	private int occupants;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="advertID")
	private AdvertisementApartment advertisementApartment;
	
	
	@ManyToOne
	@JoinColumn(name="person_ID")
	private User user;
	
//	public Booking(Date bookingDate){
//		this.bookingDate = bookingDate;
//	}
	
	public Booking(){
		
	}


	public int getRooms() {
		return rooms;
	}


	public void setRooms(int rooms) {
		this.rooms = rooms;
	}


	public int getOccupants() {
		return occupants;
	}


	public void setOccupants(int occupants) {
		this.occupants = occupants;
	}


	public Date getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}


	public Date getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public long getBookingID() {
		return bookingID;
	}


	public void setBookingID(long bookingID) {
		this.bookingID = bookingID;
	}


	public java.util.Date getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(java.util.Date bookingDate) {
		this.bookingDate = bookingDate;
	}


	public AdvertisementApartment getAdvertisementApartment() {
		return advertisementApartment;
	}


	public void setAdvertisementApartment(AdvertisementApartment advertisementApartment) {
		this.advertisementApartment = advertisementApartment;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
