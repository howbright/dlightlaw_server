package com.dlight.server.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations="classpath:push.yml")
public class AppConfig {
	
	private String gcmSenderKey;
	private String applePushPassword;
	private String senderId;
	private String senderNickname;
	private String channelId;
	private String channelHost;
	private String p12FilePath;
	private String loginPassword;
	
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelHost() {
		return channelHost;
	}

	public void setChannelHost(String channelHost) {
		this.channelHost = channelHost;
	}

	public String getGcmSenderKey() {
		return gcmSenderKey;
	}

	public void setGcmSenderKey(String gcmSenderKey) {
		this.gcmSenderKey = gcmSenderKey;
	}

	public String getApplePushPassword() {
		return applePushPassword;
	}

	public void setApplePushPassword(String applePushPassword) {
		this.applePushPassword = applePushPassword;
	}

	public String getSenderNickname() {
		return senderNickname;
	}

	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}

	public String getP12FilePath() {
		return p12FilePath;
	}

	public void setP12FilePath(String p12FilePath) {
		this.p12FilePath = p12FilePath;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
