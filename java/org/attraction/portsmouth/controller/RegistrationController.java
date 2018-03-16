package org.attraction.portsmouth.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.attraction.portsmouth.entity.User;
import org.attraction.portsmouth.service.EMailingService;
import org.attraction.portsmouth.service.RoleService;
import org.attraction.portsmouth.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@Autowired
	private EMailingService mailService;


	@RequestMapping(value = "/userregistration", method = RequestMethod.GET)
	public String listRegisteredPage(HttpServletRequest request, Map<String, Object> map) {
		map.put("user", new User());
		return "userregistration";
	}

	@RequestMapping(value = "/userregistration", method = RequestMethod.POST)
	public String addRegisteredUser(@Valid User user, BindingResult result, Model m, Map<String, Object> map) {

		if (result.hasErrors()) {
			
			return "userregistration";
		} else {
			try {
				// new user
				userService.addUser(user);
				map.put("message", "Your account has been created successfully");
				
				roleService.setRole(user.getUsername(),"ROLE_TOURIST");
				
				
				String toAddr = user.getEmail();
				String fromAddr = "admin@sas.com";

				String subject = "Your Portsmouth Attraction Login Credentials";

				String body = "Your login details are: \n" + "Username: " + user.getUsername() + "\n" + "Password: " + user.getPassword();
				mailService.sendEmailMsg(toAddr, fromAddr, subject, body);
			} catch (HibernateException e) {

				if (e.getCause().toString().contains("email")) {
					m.addAttribute("message", "Email is already taken");
				} else {
					m.addAttribute("message", "Username is already taken");
				}
				
				return "userregistration";
			}
			
			
			map.put("user", new User());
			return "userregistration";
		}

	}

}
