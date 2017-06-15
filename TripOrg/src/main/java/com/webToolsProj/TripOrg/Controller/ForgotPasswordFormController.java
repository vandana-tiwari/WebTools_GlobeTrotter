package com.webToolsProj.TripOrg.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.webToolsProj.TripOrg.DAO.LoginDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Email.SendEmail;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Person;
import com.webToolsProj.TripOrg.POJO.User;


@Controller
@RequestMapping("/forgotPassword.htm")
public class ForgotPasswordFormController {
	@Autowired
	@Qualifier("loginDAO")
	LoginDAO loginDAO;

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("person") Person person, BindingResult result) {

		return "forgotPassword";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doSubmitAction(@ModelAttribute("person") Person person, BindingResult result,HttpServletRequest request) {

		String email = request.getParameter("email");

		String forgotResult = loginDAO.forgotPassword(email);
		if(forgotResult != null){
			if(forgotResult.equalsIgnoreCase("null")){
				String message = "Unable to update password";
				System.out.println("Message:::::"+message);
			}else{
				StringBuffer sBuffer = new StringBuffer("New password:");
			       sBuffer.append(" "+ forgotResult);
			       sBuffer.append(" .Kindly reset password after login");
			       SendEmail sendMail = new SendEmail();
			       sendMail.sendEmail(email, sBuffer.toString());

			}
		}else {
			String message = "Email address not found!!!";
			System.out.println("Message:::::"+message);
		}
		return "home";
	}
}