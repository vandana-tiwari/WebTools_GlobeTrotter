package com.webToolsProj.TripOrg.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webToolsProj.TripOrg.DAO.AdvertisementApartmentDAO;
import com.webToolsProj.TripOrg.DAO.CategoryDAO;
import com.webToolsProj.TripOrg.DAO.LoginDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.AdvertisementApartment;
import com.webToolsProj.TripOrg.POJO.Category;
import com.webToolsProj.TripOrg.POJO.Person;

import java.util.List;
import java.util.Iterator;

import java.util.ArrayList;

@Controller
public class ListAdvertController {
	
	@Autowired
	@Qualifier("advertAptDAO")
	AdvertisementApartmentDAO advertAptDAO;

	@RequestMapping(value="/myListings.htm",method = RequestMethod.GET)
	protected ModelAndView listAptAdvertisement(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List advList = new ArrayList();

		try {
			HttpSession session = request.getSession();
			String postedBy = (String) session.getAttribute("loggedInUser");
			System.out.println("#####postedBy"+postedBy);
			advList = advertAptDAO.listApartments(postedBy);
			System.out.println("######advList"+advList+"........."+advList.size());

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		ModelAndView mv = new ModelAndView("myListings", "adverts", advList);
		return mv;
	}
	
	@RequestMapping(value="/viewAptAd.htm", method = RequestMethod.GET)
	protected String doSubmitAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("adv:::::"+request.getParameter("advertisement"));
		System.out.println("advN:::::"+request.getParameter("name"));
		AdvertisementApartment apt = advertAptDAO.get(Long.parseLong(request.getParameter("advertisement"))); 
		HttpSession session = request.getSession();
		session.setAttribute("selectedApt", apt);
		return "viewAptAd";
	    	
	}
	
	@RequestMapping(value="apartments.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("person") Person person, BindingResult result) {

		return "searchApartments";
	}
	
	@RequestMapping(value="listApartmets.htm", method = RequestMethod.POST)
	public String listAptAdverts(HttpServletRequest request) {
		HttpSession session =  request.getSession();
		String checkIn = request.getParameter("checkIn");
		session.setAttribute("checkInDate", checkIn);
		String checkOut = request.getParameter("checkOut");
		session.setAttribute("checkOutDate", checkOut);
		int rooms = Integer.parseInt(request.getParameter("rooms"));
		session.setAttribute("numOfRooms", rooms);
		String furnished = request.getParameter("furnished");
		int occupants = Integer.parseInt(request.getParameter("occupants"));
		session.setAttribute("occupants", occupants);
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		List resultApts = new ArrayList();
		resultApts = advertAptDAO.listApartments(checkIn, checkOut, rooms, furnished, occupants, city, state);
		if(resultApts.size()== 0){
			session.setAttribute("noApt", "null");
			return "searchApartments";
		}else{
			if(session.getAttribute("noApt") != null){
				session.removeAttribute("noApt");
			}
		System.out.println("Rresult:::::::::::"+ resultApts.size());
		for(int i=0; i<resultApts.size();i++){
			AdvertisementApartment ad = (AdvertisementApartment) resultApts.get(i);
		System.out.println("Title:::::::::::"+ ad.getTitle());
		}
		session.setAttribute("resultApts", resultApts);
		
		return "listApartments";
		}
	}
}