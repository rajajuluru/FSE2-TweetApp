package com.tweet.model;

import java.time.LocalDateTime;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Scope(
		  value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReplyTweetDetails {
//private String replyTweetId;
private String message;
private LocalDateTime tweetTime;
private String userEmail;
private Integer status;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public LocalDateTime getTweetTime() {
	return tweetTime;
}
public void setTweetTime(LocalDateTime tweetTime) {
	this.tweetTime = tweetTime;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((tweetTime == null) ? 0 : tweetTime.hashCode());
	result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ReplyTweetDetails other = (ReplyTweetDetails) obj;
	if (message == null) {
		if (other.message != null)
			return false;
	} else if (!message.equals(other.message))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (tweetTime == null) {
		if (other.tweetTime != null)
			return false;
	} else if (!tweetTime.equals(other.tweetTime))
		return false;
	if (userEmail == null) {
		if (other.userEmail != null)
			return false;
	} else if (!userEmail.equals(other.userEmail))
		return false;
	return true;
}
@Override
public String toString() {
	return "ReplyTweetDetails [message=" + message + ", tweetTime=" + tweetTime + ", userEmail=" + userEmail
			+ ", status=" + status + "]";
}




}
