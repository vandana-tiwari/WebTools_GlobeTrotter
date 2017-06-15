package com.webToolsProj.TripOrg.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webToolsProj.TripOrg.DAO.AdvertisementApartmentDAO;
import com.webToolsProj.TripOrg.DAO.BookingDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.AdvertisementApartment;
import com.webToolsProj.TripOrg.POJO.Booking;
import com.webToolsProj.TripOrg.POJO.Person;
import com.webToolsProj.TripOrg.POJO.User;

@Controller
@RequestMapping("/bookApt.htm")
public class BookApartmentController {

	@Autowired
	@Qualifier("advertAptDAO")
	AdvertisementApartmentDAO advertAptDAO;
	
	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;
	

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(HttpServletRequest request) throws Exception {
	
		HttpSession session = request.getSession();
		System.out.println("in post method");
		

		try {
			System.out.print("test");

			String userNm = (String) session.getAttribute("loggedInUser");
			System.out.println("Username@@@@@@:" + userNm);
			User user = (User) userDAO.get(userNm);
			System.out.print("user@@@@@@@@" + user.getPersonID());
			Long advertID = (Long) session.getAttribute("bookedApt");
			System.out.print("advertID@@@@@@@" + advertID);
			AdvertisementApartment apt = advertAptDAO.get(advertID);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strInDt = (String) session.getAttribute("checkInDate");
			String strOutDt = (String) session.getAttribute("checkOutDate");
			Date checkIn = sdf.parse(strInDt);
			Date checkOut = sdf.parse(strOutDt);
			System.out.println(checkIn+checkIn.toString());
			System.out.println(checkOut + checkOut.toString());

			int rooms = (Integer) session.getAttribute("numOfRooms");
			int occupants = (Integer) session.getAttribute("occupants");
			System.out.println(
					":::::::::::::::::::::::::::::::" + checkIn + "" + checkOut + "...." + rooms + ".." + occupants);
			System.out.println(":::::::::::::::::::::::::::::::" + rooms);
			System.out.println(":::::::::::::::::::::::::::::::" + occupants);
			Booking booking = bookingDAO.create(user, apt, checkIn, checkOut, rooms, occupants);
			System.out.println("Booking successful!!" + booking.getBookingID());
			session.setAttribute("bookingPDF", booking);

		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return ("downloadBooking");
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView listAptAdvertisement(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		if(session.getAttribute("loggedInUser") == null){
			session.setAttribute("redirectSearch", "yes");
			Person p = new Person();
			ModelAndView mv = new ModelAndView("loginUser", "person", p);
			return mv;
		}
		if(session.getAttribute("redirectSearch")!=null){
			
			session.removeAttribute("redirectSearch");
		}
		AdvertisementApartment advertApt = null;

		try {
			Long advertID = Long.parseLong(request.getParameter("bookedApt"));
			System.out.println("bookedApt" + advertID);
			session.setAttribute("bookedApt", advertID);


			System.out.println("valuessss222");
			advertApt = advertAptDAO.get(advertID);
			System.out.println("valuessss" + advertApt);
			System.out.println("valuessss" + advertApt.getPrice());
			System.out.println("valuessss" + advertApt.getAddress().getZip());
			System.out.println("valuessss" + advertApt.getFurnished());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView mv = new ModelAndView("confirmBooking", "advertApt", advertApt);
		return mv;
	}
}