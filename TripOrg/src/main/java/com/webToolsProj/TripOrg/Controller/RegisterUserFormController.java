package com.webToolsProj.TripOrg.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.User;


@Controller
@RequestMapping("/register.htm")
public class RegisterUserFormController {
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	

	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result,HttpServletRequest request) throws Exception {
		validator.validate(user, result);
		HttpSession session = request.getSession();
		System.out.println("in post method");
		if (result.hasErrors()) {
			return "registerUser";
		}

		try {
			if(session.getAttribute("userExists") != null){
				session.removeAttribute("userExists");
			}
			if(session.getAttribute("emailExists") != null){
				session.removeAttribute("emailExists");
			}
			System.out.println("user.getUserName()"+user.getUserName());
			boolean hE = userDAO.userExists(user.getUserName());
			System.out.println("valueeee"+hE);
			if(hE == false){
				boolean eE = userDAO.emailExists(user.getEmail().getEmailId());
				if(eE == false){
			
			User returnUser=userDAO.create(user.getUserName(), user.getPassword(), user.getEmail().getEmailId(), user.getfName(), user.getlName());

			session.setAttribute("loggedInUser", returnUser.getUserName());
			session.setAttribute("loggedInUserName", returnUser.getfName());
			session.setAttribute("isHost",null);
				}else{
					session.setAttribute("emailExists", "Email ID already exists. Please put a valid emailID");
					userDAO.delete(user);
					return "registerUser";
				}
			}else{
				session.setAttribute("userExists", "UserName already exists. Please try another userName");
				userDAO.delete(user);
				return "registerUser";
			}
			// DAO.close();
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		session.setAttribute("isHost", false);
		return "registerUser";
	}
}