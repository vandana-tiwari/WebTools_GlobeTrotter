package com.webToolsProj.TripOrg.Controller;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webToolsProj.TripOrg.DAO.LoginDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Email.SendEmail;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Person;
import com.webToolsProj.TripOrg.POJO.User;


@Controller

public class LoginUserFormController {
	private Person person;
	@Autowired
	@Qualifier("personValidator")
	PersonValidator validator;
	
	@Autowired
	@Qualifier("loginDAO")
	LoginDAO loginDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("person") Person person, BindingResult result,HttpServletRequest request, HttpServletResponse response) throws Exception {
		validator.validate(person, result);
		HttpSession session = request.getSession(true);
		String username = person.getUserName();
		String password = person.getPassword();
		System.out.println("Username:"+person.getUserName());
		System.out.println("Password:"+person.getPassword());
		System.out.println("in post method");
		if (result.hasErrors()) {
			return "loginUser";
		}

		try {
			System.out.print("logging in:::");
			System.out.print("..........");
			try{
			person = loginDAO.login(username,password);	
			System.out.println("Personnnn"+person);
			System.out.println("person.getUserName()"+person.getUserName());
			System.out.println("person.getfName()"+person.getfName());
			}catch(NullPointerException ne){
				session.setAttribute("loginStatus", "Invalid user credentials. Please retry...");
				return "loginUser";
			}
			if(session.getAttribute("loginStatus") != null){
				session.removeAttribute("loginStatus");
			}
			
			
			
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		System.out.println("Personnnn"+person);
		System.out.println("person.getUserName()"+person.getUserName());
		System.out.println("person.getfName()"+person.getfName());
		if(person != null){
			System.out.println("person.getRole()"+person.getRole());
			if(person.getRole().equalsIgnoreCase("User")){
				System.out.println("role is user");
				//HttpSession session = request.getSession(true);
				session.setAttribute("loggedInUser", person.getUserName());
				System.out.println("person.getUserName()"+person.getUserName());
				System.out.println("person.getfName()"+person.getfName());
				session.setAttribute("loggedInUserName", person.getfName());
				session.setAttribute("isHost", null);
				if(session.getAttribute("redirectSearch") != null){
					return "searchApartments";
				}else{
				return "home";
				}
	    	}else if(person.getRole().equalsIgnoreCase("Host")){
	    		System.out.println("person.getRole()"+person.getRole());
	    		System.out.println("person.getUserName()"+person.getUserName());
				System.out.println("person.getfName()"+person.getfName());
				session.setAttribute("loggedInUser", person.getUserName());
				session.setAttribute("loggedInUserName", person.getfName());
				session.setAttribute("isHost", person.getUserName());
				if(session.getAttribute("redirectSearch") != null){
					return "searchApartments";
				}else{
				return "home";
				}
	    	}
		}
		return "loginUser";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("person") Person person, BindingResult result) {
		return "loginUser";
	}
	
	@RequestMapping(value = "/updatePswd.htm", method = RequestMethod.GET)
	public String updatePasswordGet(@ModelAttribute("person") Person person, BindingResult result) {

		return "updatePswd";
	}
	
	@RequestMapping(value = "/updatePswd.htm", method = RequestMethod.POST)
	public String updatePasswordPost(@ModelAttribute("person") Person person,HttpServletRequest request, BindingResult result) {
		HttpSession session = request.getSession();
		session.getAttribute("loggedInUser");
		System.out.println("Updateeeeeeeeeeeeeeeee"+request.getParameter("userName")+ request.getParameter("currPswd")+ request.getParameter("newPswd"));
		boolean success = loginDAO.update(request.getParameter("userName"), request.getParameter("currPswd"), request.getParameter("newPswd"));
		System.out.println("valll"+success);
		if(success == true){
			System.out.println("in true");
			session.setAttribute("pswdMessage", "Password updated successfully!");
		}else{
			System.out.println("in false");
			session.setAttribute("pswdMessage", "Failed to update password");
		}
		return "home";
	}
	
	
}