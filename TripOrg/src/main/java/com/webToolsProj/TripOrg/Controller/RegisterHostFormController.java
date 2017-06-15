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

import com.webToolsProj.TripOrg.DAO.HostDAO;
import com.webToolsProj.TripOrg.DAO.UserDAO;
import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Host;
import com.webToolsProj.TripOrg.POJO.User;


@Controller
@RequestMapping("/registerHost.htm")
public class RegisterHostFormController {
	
	@Autowired
	@Qualifier("hostDAO")
	HostDAO hostDAO;
	
	@Autowired
	@Qualifier("hostValidator")
	HostValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("host") Host host, BindingResult result,HttpServletRequest request) throws Exception {
		validator.validate(host, result);
		HttpSession session = request.getSession();
		System.out.println("in post method");
		if (result.hasErrors()) {
			return "registerHost";
		}

		try {
			if(session.getAttribute("hostExists") != null){
				session.removeAttribute("hostExists");
			}
			if(session.getAttribute("emailExists") != null){
				session.removeAttribute("emailExists");
			}
			boolean hE = hostDAO.hostExists(host.getUserName());
			if(hE == false){
				boolean eE = hostDAO.emailExists(host.getEmail().getEmailId());
				if(eE == false){
					Host returnhost = hostDAO.create(host.getUserName(), host.getPassword(), host.getEmail().getEmailId(), host.getfName(), host.getlName(), host.getGovtID(), host.getIdType());
					
					session.setAttribute("isHost", returnhost.getUserName());
					session.setAttribute("loggedInUser", returnhost.getUserName());
					session.setAttribute("loggedInUserName", returnhost.getfName());					
				}else{
					session.setAttribute("emailExists", "Email ID already exists. Please put a valid emailID");
					hostDAO.delete(host);
					return "registerHost";
				}
			}else{
				session.setAttribute("hostExists", "UserName already exists. Please try another userName");
				hostDAO.delete(host);
				return "registerHost";
			}
			
		} catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("host") Host host, BindingResult result,HttpServletRequest request) {

		return "registerHost";
	}
}