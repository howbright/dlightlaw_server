/**
 * 
 */
package com.dlight.server.repository.entity;

/**
 * `user`.`user_id`, `user`.`name`, `user`.`phone_number`, `user`.`company`,
 * `user`.`department`, `user`.`position`, `user`.`email`,
 * `user`.`company_phone_number`, `user`.`company_fax`, `user`.`company_address`
 *
 */
public class User {

	private int userId;
	private String name;
	private String phoneNumber;
	private String company;
	private String department;
	private String position;
	private String email;
	private String companyPhoneNumber;
	private String companyFax;
	private String companyAddress;
	private boolean allowMessageFlag;
	private long interestIndustry;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyPhoneNumber() {
		return companyPhoneNumber;
	}

	public void setCompanyPhoneNumber(String companyPhoneNumber) {
		this.companyPhoneNumber = companyPhoneNumber;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public boolean isAllowMessageFlag() {
		return allowMessageFlag;
	}

	public void setAllowMessageFlag(boolean allowMessageFlag) {
		this.allowMessageFlag = allowMessageFlag;
	}

	public long getInterestIndustry() {
		return interestIndustry;
	}

	public void setInterestIndustry(long interestIndustry) {
		this.interestIndustry = interestIndustry;
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof User) {
			if (((User) other).getUserId() == this.userId) {
				return true;
			}
		}
		return false;
	}
}