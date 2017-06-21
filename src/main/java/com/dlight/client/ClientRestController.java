package com.dlight.client;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {
	
	 @Autowired AuthenticationManager authenticationManager;
     @Autowired ClientService clientService;
     
     @RequestMapping(value="/login", method=RequestMethod.POST)
     public AuthenticationToken login(
               @RequestBody AuthenticationRequest authenticationRequest,
               HttpSession session
               ) {
          String username = authenticationRequest.getUsername();
          String password = authenticationRequest.getPassword();
          
          UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
          Authentication authentication = authenticationManager.authenticate(token);
          SecurityContextHolder.getContext().setAuthentication(authentication);
          session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());
          
          Client user = (Client) clientService.loadUserByUsername(username);
          return new AuthenticationToken(user.getCompanyName(), user.getAuthorities(), session.getId());
     }


	
//	@CrossOrigin("*")
//	@RequestMapping(value="/test", method = RequestMethod.POST)
//	public String test(String name, String password) {
//		System.out.println("ada");
//		return "templates/index.html";
//	}

}
