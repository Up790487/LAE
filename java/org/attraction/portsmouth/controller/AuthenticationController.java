package  org.attraction.portsmouth.controller;

import org.attraction.portsmouth.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("user") User user,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "error", required = false) String error
			) {

		ModelAndView model = new ModelAndView();
		model.addObject("user", new User());
		if (logout != null) {
			model.addObject("message", "You have logged out successful");
		}
		if (error != null) {
			model.addObject("error", "You have specified the wrong username or password");
		}

		model.setViewName("login");

		return model;

	}


}
