package com.tweet.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tweet.helperClass.RegisterHelperClass;

@Document
@Scope(
		  value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TweetsDetails {
	
	@Id
	
	private String tweetId;
	private String userEmail;
	private String message;
	private LocalDate tweetdate;
	private List<String> tagUsers;
	private Integer status;//0-open ,1 --deleted
	private List<RegisterHelperClass> likedUsersList;
	private List<ReplyTweetDetails> repliedUsersList;
	public List<RegisterHelperClass> getLikedUsersList() {
		return likedUsersList;
	}
	public void setLikedUsersList(List<RegisterHelperClass> likedUsersList) {
		this.likedUsersList = likedUsersList;
	}
	public List<ReplyTweetDetails> getRepliedUsersList() {
		return repliedUsersList;
	}
	public void setRepliedUsersList(List<ReplyTweetDetails> repliedUsersList) {
		this.repliedUsersList = repliedUsersList;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDate getTweetdate() {
		return tweetdate;
	}
	public void setTweetdate(LocalDate tweetdate) {
		this.tweetdate = tweetdate;
	}
	public List<String> getTagUsers() {
		return tagUsers;
	}
	public void setTagUsers(List<String> tagUsers) {
		this.tagUsers = tagUsers;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TweetsDetails [tweetId=" + tweetId + ", userEmail=" + userEmail + ", message=" + message
				+ ", tweetdate=" + tweetdate + ", tagUsers=" + tagUsers + ", status=" + status + ", likedUsersList="
				+ likedUsersList + ", repliedUsersList=" + repliedUsersList + "]";
	}


	
	
	

}
