package com.webToolsProj.TripOrg.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webToolsProj.TripOrg.DAO.LoginDAO;
import com.webToolsProj.TripOrg.POJO.User;


@Controller
@RequestMapping("/logout.htm")
public class LogOutUserFormController {
	
	@Autowired
	@Qualifier("loginDAO")
	LoginDAO loginDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result,HttpServletRequest request) {

		HttpSession session = request.getSession();
		System.out.print("Logging out..........");
		loginDAO.logout(session);
		return "home";
	}
}