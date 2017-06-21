package com.dlight.client;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Client implements UserDetails{

	private static final long serialVersionUID = 1L;
	private int cleintId;
	private String companyName;
	private String password;
	private int defaultClientId;
	private int researchReqLimit;
	private int precedentReqLimit;
	private int legalDownloadLimit;
	private long serviceAvailableTime;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	private Collection<? extends GrantedAuthority> authorities;

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return companyName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public int getCleintId() {
		return cleintId;
	}

	public void setCleintId(int cleintId) {
		this.cleintId = cleintId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getDefaultClientId() {
		return defaultClientId;
	}

	public void setDefaultClientId(int defaultClientId) {
		this.defaultClientId = defaultClientId;
	}

	public int getResearchReqLimit() {
		return researchReqLimit;
	}

	public void setResearchReqLimit(int researchReqLimit) {
		this.researchReqLimit = researchReqLimit;
	}

	public int getPrecedentReqLimit() {
		return precedentReqLimit;
	}

	public void setPrecedentReqLimit(int precedentReqLimit) {
		this.precedentReqLimit = precedentReqLimit;
	}

	public int getLegalDownloadLimit() {
		return legalDownloadLimit;
	}

	public void setLegalDownloadLimit(int legalDownloadLimit) {
		this.legalDownloadLimit = legalDownloadLimit;
	}

	public long getServiceAvailableTime() {
		return serviceAvailableTime;
	}

	public void setServiceAvailableTime(long serviceAvailableTime) {
		this.serviceAvailableTime = serviceAvailableTime;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
