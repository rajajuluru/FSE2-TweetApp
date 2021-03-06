package com.tweet.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.helperClass.Login;
import com.tweet.helperClass.RegisterHelperClass;
import com.tweet.helperClass.ResponseHelper;
import com.tweet.model.ReplyTweetDetails;
import com.tweet.model.TweetsDetails;
import com.tweet.model.service.TweeterService;

@RestController
@RequestMapping(value = "/api/v1.0/tweets")
// @CrossOrigin("http://localhost:4200")
public class HomeController {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private TweeterService tweeterService;

	@PostMapping(value = "/register")
	public ResponseHelper Register(@RequestBody RegisterHelperClass registerHelperClass) {
		System.out.println(registerHelperClass.toString());
		ResponseHelper register = tweeterService.Register(registerHelperClass);
		return register;
	}

	@GetMapping(value = "/getTweets/{emailid}")
	public ResponseHelper Register(@PathVariable("emailid") String emailid) {
		System.out.println(emailid.trim()+"emailidemailid");
		ResponseHelper register = tweeterService.getTweetByUsers(emailid.trim());
		System.out.println(register.toString()+"getTweets register.toString()");
		return register;
	}

	@PostMapping(value = "/login")
	public RegisterHelperClass Register(@RequestBody Login login) {
		System.out.println(login.toString());
		RegisterHelperClass findById = mongoTemplate.findById(login.getEmail(), RegisterHelperClass.class);
		Optional<RegisterHelperClass> of = Optional.ofNullable(findById);
		System.out.println(of.isPresent() + "is present");
		System.out.println();
		return findById;
	}

	@GetMapping(value = "/getUserDetails/{email}")
	public RegisterHelperClass getUserDetails(@PathVariable(name = "email") String emailid) {
		System.out.println(emailid + "emailid");
		RegisterHelperClass findById = mongoTemplate.findById(emailid, RegisterHelperClass.class);
		Optional<RegisterHelperClass> of = Optional.ofNullable(findById);
		System.out.println(of.isPresent() + "is present");
		System.out.println();
		return findById;
	}

	@GetMapping(value = "/all")
	public ResponseHelper getAllTweets() {

		ResponseHelper allTweets = tweeterService.getAllTweets();
		return allTweets;

	}

	@PostMapping(value = "/addTweet")
	public ResponseHelper postTweet(@RequestBody TweetsDetails details) {
		ResponseHelper res = new ResponseHelper(false, "");
		System.out.println(details.toString() +"tweet data in tweetapplication");
		if (details.getMessage().length() > 144) {
			res.setData("tweetLimitCrossed");
			res.setStatus(false);
			return res;
		} else {
			details.setStatus(0);
			details.setTweetdate(LocalDate.now());
			// details.setUserEmail("2042954@cognizant.com");
			// details.set
			List<String> tagUsers = details.getTagUsers();
			List<String> toberemoved = new ArrayList<>();
			Optional<List<String>> ofNullable2 = Optional.ofNullable(tagUsers);
			try {
				if (ofNullable2.isPresent()) { // if (tagUsers.size() > 0)
					tagUsers.stream().forEach((z) -> {
						RegisterHelperClass findById = mongoTemplate.findById(z, RegisterHelperClass.class);
						Optional<RegisterHelperClass> ofNullable = Optional.ofNullable(findById);
						if (ofNullable.isPresent()) {

						} else {
							toberemoved.add(z);
						}

					});

					tagUsers.removeAll(toberemoved);
				} else {
					System.out.println("no tagged users");
				}

				TweetsDetails save = mongoTemplate.save(details);
				res.setData(save);
				res.setStatus(true);
			} catch (Exception e) {
				res.setData(e.getMessage());
				res.setStatus(false);
				// TODO: handle exception
				e.printStackTrace();
			}
			return res;
		}

	}

	@GetMapping(value = "/allUsers")
	public ResponseHelper allUsers() {
		Query query = new Query();
		query.fields().exclude("password");

		List<RegisterHelperClass> find = mongoTemplate.find(query, RegisterHelperClass.class);
		ResponseHelper res = new ResponseHelper(true, find);
		return res;

	}

	@PatchMapping(value = "/updateTweet/{tweetid}")
	public ResponseHelper updateTweet(@PathVariable(name = "tweetid") String tweetid,
			@RequestBody TweetsDetails details) {

		return tweeterService.updateTweet(tweetid, details);

	}

	@GetMapping(value = "/deleteTweet/{tweetid}")
	public ResponseHelper deleteTweet(@PathVariable(name = "tweetid") String tweetid) {

		ResponseHelper deleteTweet = tweeterService.deleteTweet(tweetid);
		return deleteTweet;

	}

	@GetMapping(value = "/likeTweet/{tweetid}/{useremail}")
	public ResponseHelper updateTweet(@PathVariable(name = "tweetid") String tweetid,
			@PathVariable(name = "useremail") String useremail) {
		ResponseHelper likeTweet = null;

		try {
			likeTweet = tweeterService.likeTweet(tweetid, useremail);
			return likeTweet;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ResponseHelper responseHelper = new ResponseHelper(false, "something went wrong");
			return responseHelper;
		}

	}

	@PostMapping(value = "/replyTweet/{tweetid}/{useremail}")
	public ResponseHelper replyTweet(@PathVariable(name = "tweetid") String tweetid,
			@PathVariable(name = "useremail") String useremail, @RequestBody ReplyTweetDetails details) {
		System.out.println(details + "details ");
		ResponseHelper readTweet = null;

		try {
			readTweet = tweeterService.ReplyTweet(tweetid, details, useremail);
			System.out.println(readTweet+"response in reply tweet");
			return readTweet;
		} catch (Exception e) {
			// TODO: handle exception
			ResponseHelper responseHelper = new ResponseHelper(false, "something went wrong");
			return responseHelper;
		}

	}

	@GetMapping(value = "users/all")
	public ResponseHelper getAllUsers() {

		return tweeterService.getAllUsers();

	}

	@GetMapping(value = "/user/search/{userName}")
	public ResponseHelper getuserName(@PathVariable(name = "userName") String userName) {

		return tweeterService.SearchByUsers(userName);

	}

	@GetMapping(value = "/{username}")
	public ResponseHelper getAllTweets(@PathVariable(name = "username") String userName) {

		return tweeterService.getAllTweetsByUsers(userName);

	}
	
	@GetMapping(value = "/disliketweet/{tweetid}/{emailid}")
	public ResponseHelper disliketweet(@PathVariable(name = "tweetid") String tweetid,@PathVariable(name = "emailid") String emailid) {

		ResponseHelper dislikeTweet = tweeterService.dislikeTweet(tweetid, emailid);
		return dislikeTweet;

	}

}
