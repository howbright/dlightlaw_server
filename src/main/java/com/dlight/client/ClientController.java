package com.dlight.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
	
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@CrossOrigin("*")
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String welcome(String name, String password) {
		return "index";
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Map<String, Object> model) {
		model.put("message", this.message);
		return "loginform2";
	}
	
	@CrossOrigin("*")
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test(Map<String, Object> model) {
		System.out.println("ada");
		return "loginform2";
	}
	

	
//	// inject via application.properties
//		@Value("${welcome.message:test}")
//		private String message = "Hello World";
//
//		@RequestMapping("/")
//		public String welcome(Map<String, Object> model) {
//			model.put("message", this.message);
//			return "welcome";
//		}
}
