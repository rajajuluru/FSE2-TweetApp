package com.tweet.model.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tweet.helperClass.RegisterHelperClass;
import com.tweet.helperClass.ResponseHelper;
import com.tweet.model.ReplyTweetDetails;
import com.tweet.model.TweetsDetails;

@Service
public interface TweeterService {

	public ResponseHelper likeTweet(String tweetid, String userEmail);

	public ResponseHelper ReplyTweet(String tweetid, ReplyTweetDetails tweetDetails,String useremail);

	public ResponseHelper getAllUsers();
	public ResponseHelper SearchByUsers(String uname);
	public ResponseHelper getAllTweetsByUsers(String uname);
	public ResponseHelper deleteTweet(String tweetid);
	public ResponseHelper updateTweet(String tweetid,TweetsDetails details);
	
	public ResponseHelper getAllTweets();
	public ResponseHelper Register(RegisterHelperClass registerHelperClass);
	
	public ResponseHelper getTweetByUsers(String emailid);
	public ResponseHelper dislikeTweet(String tweetid,String emailid);
	
	
	

}
