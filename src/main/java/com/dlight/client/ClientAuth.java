package com.dlight.client;

public enum ClientAuth {
	ADMIN(0,"ADMIN"),
	USER(1, "USER"),
	NONE(2, "NONE");

	private final int code;
	private final String name;
	
	private ClientAuth(int code, String name){
		this.code = code;
		this.name = name;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
	
	public static ClientAuth authFromCode(int code){
		switch (code) {
		case 0:
			return ADMIN;
		case 1:
			return USER;
		}
		return NONE;
	}
}
