package com.dlight.contract;

import java.util.List;

public class Article {

	private String title = "";
	private String originData = "";
	private String userData = "";
	private int articleIndex;
	private int orderIndex;
	private List<Condition> conditions;
	private boolean isOptional = false;

	public Article() {
	}

	public Article(Builder b) {
		this.title = b.title;
		this.originData = b.originData;
		this.userData = b.userData;
		this.articleIndex = b.articleIndex;
		this.orderIndex = b.orderIndex;
		this.conditions = b.conditions;
		this.isOptional = b.isOptional;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginData() {
		return originData;
	}

	public void setOriginData(String originData) {
		this.originData = originData;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	public int getArticleIndex() {
		return articleIndex;
	}

	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public void addOriginalData(String text) {
		this.originData = this.originData + text;
	}

	public void addUserData(String text) {
		this.userData = this.userData + text;
	}

	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public static class Builder {
		private String title="";
		private String originData="";
		private String userData="";
		private int articleIndex;
		private int orderIndex;
		private List<Condition> conditions;
		private boolean isOptional = false;

	    public Builder() {
	    }

	    public Builder title(String value) {
	      title = value;
	      return this;
	    }

	    public Builder originData(String value) {
	      originData = value;
	      return this;
	    }

	    public Builder userData(String value) {
	      userData = value;
	      return this;
	    }
	    
	    public Builder articleIndex(int value) {
		      articleIndex = value;
		      return this;
		    }
	    public Builder orderIndex(int value) {
		      orderIndex = value;
		      return this;
		    }
	    public Builder conditions(List<Condition> value) {
		      conditions = value;
		      return this;
		    }
	    public Builder isOptional(boolean value) {
		      isOptional = value;
		      return this;
		    }

	    public Article build() {
	      return new Article(this);
	    }
	}
}
