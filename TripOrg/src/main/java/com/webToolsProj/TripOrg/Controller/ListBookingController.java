package com.webToolsProj.TripOrg.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webToolsProj.TripOrg.DAO.BookingDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.POJO.User;

import java.util.List;

import java.util.ArrayList;

@Controller
// @RequestMapping("/myListings.htm")
public class ListBookingController {
	
	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;
	
	@Autowired
	@Qualifier("bookingDAO")
	BookingDAO bookingDAO;

	@RequestMapping(value = "/myBookings.htm", method = RequestMethod.GET)
	protected ModelAndView listUserBookings(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List bookings = new ArrayList();

		try {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("loggedInUser");

			User u = (User) userDAO.get(username);

			bookings = bookingDAO.listUserBookings(u.getPersonID());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+bookings.size()+">>"+u.getPersonID());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ModelAndView mv = new ModelAndView("myBookings", "bookings", bookings);
		return mv;
	}

}