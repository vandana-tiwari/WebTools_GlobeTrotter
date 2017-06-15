package com.webToolsProj.TripOrg.DAO;


import java.util.Date;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.AdvertisementApartment;
import com.webToolsProj.TripOrg.POJO.Booking;
import com.webToolsProj.TripOrg.POJO.User;


public class BookingDAO extends DAO {
	
	public BookingDAO() {
    }

    public Booking create(User user, AdvertisementApartment advertisementApartment, Date checkIn, Date checkOut, int rooms, int occupants)
            throws AdException {
        try {
            begin();
            Date date = new Date();
            Booking booking = new Booking();
            booking.setBookingDate(date);
            booking.setCheckInDate(checkIn);
            booking.setCheckOutDate(checkOut);
            booking.setOccupants(occupants);
            booking.setRooms(rooms);
            booking.setAdvertisementApartment(advertisementApartment);
            System.out.println("advertisementApartment@@@@@@@@@@"+advertisementApartment.getAdvertID());
            booking.setUser(user);
            getSession().save(booking);
            commit();
            return booking;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advertisementApt", e);
            throw new AdException("Exception while creating booking: " + e.getMessage());
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
    
    public List listUserBookings(long personID){
    	begin();
    	Query q = getSession().createQuery("from Booking where person_ID = :personID");
        q.setLong("personID", personID);
        List bookings = q.list();
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,"+bookings.size());
        commit();
        return bookings;
    }
    
    public List listHostBookings(long advertID){
    	begin();
    	Query q = getSession().createQuery("from Booking where advertID = :advertID");
        q.setLong("advertID", advertID);
        List bookings = q.list();
        commit();
        return bookings;
    }
    
}