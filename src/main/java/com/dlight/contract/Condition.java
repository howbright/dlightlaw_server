package com.dlight.contract;

public class Condition {
	
	private String type;
	private String questionKey;
	private String userInsertValue;
	private String conId;
	
	public Condition() {
		super();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestionKey() {
		return questionKey;
	}

	public void setQuestionKey(String questionKey) {
		this.questionKey = questionKey;
	}
	
	public String getUserInsertValue() {
		return userInsertValue;
	}

	public void setUserInsertValue(String userInsertValue) {
		this.userInsertValue = userInsertValue;
	}

	public String getConId() {
		return conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}
}
