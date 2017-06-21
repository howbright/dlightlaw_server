package com.dlight.client;

import java.util.List;

public interface ClientMapper {
	public Client readClient(String companyName);
	public List<Integer> readAuthority(String companyName);
}
