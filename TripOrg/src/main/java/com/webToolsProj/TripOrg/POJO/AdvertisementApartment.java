package com.webToolsProj.TripOrg.POJO;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="advertisementAptTable")
@PrimaryKeyJoinColumn(name="advertID")
public class AdvertisementApartment{

	@Id
	@GeneratedValue
	@Column(name="advertID", unique = true, nullable = false)
	private long advertID;
	
	
    private String title;
    private String message;
    private String postedBy;
    private String category_name;
    private long category;
    private String photoName;
    @Transient
    private MultipartFile photo;
	@Column(name="price")
	private float price;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="user")
    private Person person;
	
	//@OneToOne(fetch=FetchType.EAGER, mappedBy="advertisementApartment", cascade=CascadeType.ALL)
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id")
	private Address address;
	
	@Column(name="rooms")
	private int rooms;
	
	@Column(name="numOfBeds")
	private int numOfBeds;
	
	@Column(name="bathrooms")
	private float bathrooms;
	
	@Column(name="maxOccupants")
	private int maxOccupants;
	
	@Column(name="priceExtraOccupant")
	private float priceExtraOccupant;
	
	@Column(name="furnished")
	private String furnished;
	
	@Column(name="checkIn")
	private Date checkIn;
	
	@Column(name="checkOut")
	private Date checkOut;
	
	public AdvertisementApartment(float price, Address address, int rooms, int numOfBeds,float bathrooms,int maxOccupants,float priceExtraOccupant,String furnished,Date checkIn,Date checkOut) {
		this.price = price;
		this.address = address;
		this.rooms = rooms;
		this.numOfBeds = numOfBeds;
		this.bathrooms = bathrooms;
		this.maxOccupants = maxOccupants;
		this.priceExtraOccupant = priceExtraOccupant;
		this.furnished = furnished;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	AdvertisementApartment(){
		
	}

	public long getAdvertID() {
		return advertID;
	}

	public void setAdvertID(long advertID) {
		this.advertID = advertID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	public float getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(float bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getMaxOccupants() {
		return maxOccupants;
	}

	public void setMaxOccupants(int maxOccupants) {
		this.maxOccupants = maxOccupants;
	}

	public float getPriceExtraOccupant() {
		return priceExtraOccupant;
	}

	public void setPriceExtraOccupant(float priceExtraOccupant) {
		this.priceExtraOccupant = priceExtraOccupant;
	}

	public String getFurnished() {
		return furnished;
	}

	public void setFurnished(String furnished) {
		this.furnished = furnished;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

}
