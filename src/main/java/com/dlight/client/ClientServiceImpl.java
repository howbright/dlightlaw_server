package com.dlight.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	ClientMapper clientMapper;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String clientname) throws UsernameNotFoundException {
		
		Client client = clientMapper.readClient(clientname);
		client.setAuthorities(getAuthorities(clientname));
		return client;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities(String clientname) {
		 List<Integer> authorityCodes = clientMapper.readAuthority(clientname);
		 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 for (Integer code : authorityCodes) {
             authorities.add(new SimpleGrantedAuthority(ClientAuth.authFromCode(code).getName()));
        }
        return authorities;
	}

}
