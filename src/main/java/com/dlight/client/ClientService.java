package com.dlight.client;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClientService extends UserDetailsService{
	Collection<GrantedAuthority> getAuthorities(String username);
}
