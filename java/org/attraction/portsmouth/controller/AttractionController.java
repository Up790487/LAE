package  org.attraction.portsmouth.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.attraction.portsmouth.entity.Attraction;
import org.attraction.portsmouth.entity.Search;
import org.attraction.portsmouth.entity.User;
import org.attraction.portsmouth.service.AttractionService;
import org.attraction.portsmouth.service.EMailingService;
import org.attraction.portsmouth.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AttractionController {
	@Autowired
	private AttractionService attractionService;
	
	@Autowired
	private EMailingService mailingService;
	
	@Autowired
	private UserService userService;
	
	

	@RequestMapping(value = "/attraction",method = RequestMethod.GET)
	public String listAttraction(HttpServletRequest request, Map<String, Object> map ) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			map.put("attraction", new Attraction());
			map.put("search", new Search());
			if (userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]")) {
				map.put("attractionList", attractionService.listAllAttraction(userDetail.getUsername()));

			}else{
				map.put("attractionList", attractionService.listAllAttraction());
			}
		}
		return "attraction";
	}

	@RequestMapping(value = "/attraction", method = RequestMethod.POST)
	public String addAttraction(HttpServletRequest request,@Valid Attraction attraction,BindingResult result, Model m, Map<String, Object> map, 
			@RequestParam(value = "picture", required = false) MultipartFile imageFile,RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				if (userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]")) {
					map.put("attractionList", attractionService.listAllAttraction(userDetail.getUsername()));

				} else{
					map.put("attractionList", attractionService.listAllAttraction());
				}
			}
			if(!imageFile.isEmpty()){
				attraction.setPicture(imageFile.getOriginalFilename());
			}
			return "attraction";
		} else {
			try {
				
				//upload the file

                if(!imageFile.isEmpty()){
                	if(!imageFile.getOriginalFilename().endsWith(".jpeg") &&
                		!imageFile.getOriginalFilename().endsWith(".png")&&
                		!imageFile.getOriginalFilename().endsWith(".jpg")&&
                		  !imageFile.getOriginalFilename().endsWith(".gif")
                		 ){
                		map.put("message",
        						"Files jpg, jpeg, gif and png are the only files accepted");
                		return "attraction";
                	}
                	File imageFileToSend = new File(request.getRealPath("/resources/images/"),attraction.getUsername()+attraction.getName()+imageFile.getOriginalFilename());
                	
    				attraction.setPicture(imageFile.getOriginalFilename());
    				
    				try {
						imageFile.transferTo(imageFileToSend);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				
    			}
				if (attraction.getIdattraction() == null) {
					
					attractionService.addAttraction(attraction);
					redirectAttributes.addFlashAttribute("message",  attraction.getName()+" has been saved");
					
				} else {
				
					attractionService.updateAttraction(attraction);
					
					redirectAttributes.addFlashAttribute("message", attraction.getName()+" has been updated");
					
				}

			} catch (HibernateException e) {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (!(auth instanceof AnonymousAuthenticationToken)) {
					UserDetails userDetail = (UserDetails) auth.getPrincipal();
					if (userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]")) {
						map.put("attractionList", attractionService.listAllAttraction(userDetail.getUsername()));

					} else {
						map.put("attractionList", attractionService.listAllAttraction());
					}
				}
				
				redirectAttributes.addFlashAttribute("message", "This Attraction name is already taken");
				map.put("attraction", new Attraction());
				
				return "redirect:attraction";
			} 
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				if (userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]")) {
					map.put("attractionList", attractionService.listAllAttraction(userDetail.getUsername()));

				} else{
					map.put("attractionList", attractionService.listAllAttraction());
				}
			}
			map.put("attraction", new Attraction());
			
			return "redirect:attraction";

		}

	}
	
	@RequestMapping("/updateattraction/{attractionId}")
	public String updateAttraction(@PathVariable("attractionId") Integer attractionId, Map<String, Object> map) {

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    	if (!userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]") && 
	    			!userDetail.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
				return "redirect:/home";
			}
	    }
	    
		
			
		map.put("attraction", attractionService.getAttractionById(attractionId));

		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			if (userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]")) {
				map.put("attractionList", attractionService.listAllAttraction(userDetail.getUsername()));

			 }else{
				map.put("attractionList", attractionService.listAllAttraction());
			}
		}
		return "attraction";
	}
	
	@RequestMapping("/deleteattraction/{attractionId}")
	public String deleteAttraction(@PathVariable("attractionId") Integer attractionId, Map<String, Object> map) {

	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	    	UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    	if (!userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]") && 
	    			!userDetail.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
				return "redirect:/home";
			}
	    }
	    
		
			attractionService.removeAttractionById(attractionId);
				
				if (!(auth instanceof AnonymousAuthenticationToken)) {
					UserDetails userDetail = (UserDetails) auth.getPrincipal();
					if (userDetail.getAuthorities().toString().equals("[ROLE_TOURIST]")) {
						map.put("attractionList", attractionService.listAllAttraction(userDetail.getUsername()));

					} else{
						map.put("attractionList", attractionService.listAllAttraction());
					}
				}
				map.put("attraction", new Attraction());
				return "attraction";
	}
	
	
	
	@RequestMapping(value="/rateattraction/{attractionId}")
	public String rateAttraction(@PathVariable("attractionId") Integer attractionId, Map<String, Object> map,
			RedirectAttributes redirectAttributes) {
		// store ratings - TODO
		
		
		redirectAttributes.addFlashAttribute("message",
				"Your rating has been received");
		
		return "redirect:/attraction";
	}
	
	
	
	
	
	
}
