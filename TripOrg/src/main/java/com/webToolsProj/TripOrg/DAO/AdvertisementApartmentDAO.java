package com.webToolsProj.TripOrg.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Address;
import com.webToolsProj.TripOrg.POJO.AdvertisementApartment;
import com.webToolsProj.TripOrg.POJO.Person;


public class AdvertisementApartmentDAO extends DAO {
	
	public AdvertisementApartmentDAO() {
    }

    public AdvertisementApartment create(String title, String photoName, String message, Person person,long category_id, String category_name,float price, String street, String city, String state, int zip, int rooms, int numOfBeds,float bathrooms,int maxOccupants,float priceExtraOccupant,String furnished,Date checkIn,Date checkOut)
            throws AdException {
        try {
            begin();
            Address address = new Address(street,city,state,zip);
            AdvertisementApartment advertisementApt = new AdvertisementApartment(price,address,rooms,numOfBeds,bathrooms,maxOccupants,priceExtraOccupant,furnished,checkIn,checkOut);
            
            advertisementApt.setPostedBy(person.getUserName());
            System.out.println("person.getUserName()@@@@@@@@@@"+person.getUserName()+person);
            advertisementApt.setTitle(title);
            advertisementApt.setMessage(message);
            advertisementApt.setPerson(person);
            advertisementApt.setCategory_name(category_name);
            advertisementApt.setCategory(category_id);
            advertisementApt.setPhotoName(photoName);
            getSession().save(advertisementApt);
            commit();
            return advertisementApt;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advertisementApt", e);
            throw new AdException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(AdvertisementApartment advertisementApt)
            throws AdException {
        try {
            begin();
            getSession().delete(advertisementApt);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete advert", e);
        }
    }
    
    public List listApartments(String postedBy){
    	begin();
    	Query q = getSession().createQuery("from AdvertisementApartment where postedBy = :postedBy");
        q.setString("postedBy", postedBy);
        List advAptList = q.list();
        commit();
        return advAptList;
    }
    
    public List listApartments(String checkIn, String checkOut,int rooms, String furnished,int occupants, String city, String state){
    	begin();
    	System.out.println("checkIn::"+checkIn);
    	System.out.println("cout"+checkOut);
    	Query q = getSession().createQuery("from AdvertisementApartment adApt where adApt.advertID not in (Select b.advertisementApartment.advertID from Booking b where b.checkInDate <=:checkInDate and b.checkOutDate >=:checkOutDate) and adApt.checkIn <= :checkIn and adApt.checkOut >= :checkOut and adApt.rooms >= :rooms and adApt.furnished= :furnished and adApt.address.city= :city and adApt.address.state= :state");
    	q.setString("checkInDate", checkIn);
        q.setString("checkOutDate", checkOut);
        q.setString("checkIn", checkIn);
        q.setString("checkOut", checkOut);
        q.setInteger("rooms", rooms);
        q.setString("furnished", furnished);
        q.setString("city", city);
        q.setString("state", state);
        List advAptList = q.list();
        commit();
        return advAptList;
    }
    
    
    public AdvertisementApartment get(long advertID){
    	begin();
    	Query q = getSession().createQuery("from AdvertisementApartment where advertID = :advertID");
        q.setLong("advertID", advertID);
        AdvertisementApartment apt = (AdvertisementApartment)q.uniqueResult();
        commit();
        return apt;
    }
}