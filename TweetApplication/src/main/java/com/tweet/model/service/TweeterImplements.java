package com.tweet.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.tweet.helperClass.RegisterHelperClass;
import com.tweet.helperClass.ResponseHelper;
import com.tweet.model.ReplyTweetDetails;
import com.tweet.model.TweetsDetails;

@Service
public class TweeterImplements implements TweeterService {

	@Autowired
	private MongoTemplate template;

	@Override
	public ResponseHelper likeTweet(String tweetid, String userEmail) {
		// TODO Auto-generated method stub
		ResponseHelper res = new ResponseHelper(false, null);
		System.out.println(tweetid + "     tweetid" + "                  userEmail      " + userEmail);
		TweetsDetails tweetdetails = template.findById(tweetid, TweetsDetails.class);
		Optional<TweetsDetails> optionalTweetDetails = Optional.ofNullable(tweetdetails);
		if (optionalTweetDetails.isPresent()) {
			if (optionalTweetDetails.get().getStatus().equals(0)) {
				Query query = new Query();
				query.addCriteria(Criteria.where("email").is(userEmail));
				query.fields().exclude("password");
				RegisterHelperClass likedUserDetails = template.findOne(query, RegisterHelperClass.class);
				Optional<RegisterHelperClass> likedUserOptional = Optional.ofNullable(likedUserDetails);
				if (likedUserOptional.isPresent()) {
					List<RegisterHelperClass> likedUsersList = tweetdetails.getLikedUsersList();
					try {

						likedUsersList.add(likedUserDetails);

					} catch (Exception e) {
						// TODO: handle exception

						likedUsersList = new ArrayList<>();
						likedUsersList.add(likedUserDetails);
					}
					tweetdetails.setLikedUsersList(likedUsersList);
					template.save(tweetdetails);
					res.setData(tweetdetails);
					res.setStatus(true);
					return res;

				} else {
					res.setData("user details not found");
					res.setStatus(false);
					return res;
				}
			} else {

				res.setData("user details not found tweet no longer exists");
				res.setStatus(false);
				return res;
			}

		} else {
			res.setData("tweetid not found");
			res.setStatus(false);
			return res;
		}

	}

	@Override
	public ResponseHelper ReplyTweet(String tweetid, ReplyTweetDetails tweetDetails, String useremail) {
		// TODO Auto-generated method stub
		ResponseHelper res = new ResponseHelper(false, null);
		if (tweetDetails.getMessage().length() > 0) {
			TweetsDetails maintweetdetails = template.findById(tweetid.trim(), TweetsDetails.class);
			Optional<TweetsDetails> Optioanlmaintweetdetails = Optional.ofNullable(maintweetdetails);
			if (Optioanlmaintweetdetails.isPresent()) {

				if (!tweetDetails.getMessage().trim().isEmpty()) {
					List<ReplyTweetDetails> repliedUsersList = maintweetdetails.getRepliedUsersList();
					tweetDetails.setStatus(0);

					tweetDetails.setTweetTime(LocalDateTime.now());
					RegisterHelperClass findById = template.findById(useremail, RegisterHelperClass.class);
					Optional<RegisterHelperClass> ofNullable = Optional.ofNullable(findById);
					if (ofNullable.isPresent()) {
						tweetDetails.setUserEmail(useremail);
						try {
							repliedUsersList.add(tweetDetails);

						} catch (Exception e) {
							// TODO: handle exception
							repliedUsersList = new ArrayList<>();
							repliedUsersList.add(tweetDetails);
						}
						maintweetdetails.setRepliedUsersList(repliedUsersList);

						TweetsDetails save = template.save(maintweetdetails);
						res.setData(save);
						res.setStatus(true);
						return res;
					} else {
						res.setData("user not found with given mailid");
						res.setStatus(false);
						return res;
					}

				} else {
					res.setData("message cannot be empty");
					res.setStatus(false);
					return res;
				}
			} else {
				res.setData("tweet cannot find something went wrong");
				res.setStatus(false);
				return res;
			}
		} else {
			res.setData("something went wrong");
			res.setStatus(false);
			return res;
		}

	}

	@Override
	public ResponseHelper getAllUsers() {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.fields().exclude("password");
		List<RegisterHelperClass> find = template.find(query, RegisterHelperClass.class);
		System.out.println(find + "find all data");
		ResponseHelper res = new ResponseHelper(true, find);
		return res;
	}

	@Override
	public ResponseHelper SearchByUsers(String uname) {
		// TODO Auto-generated method stub
		Query q = new Query();
		q.addCriteria(Criteria.where("email").is(uname));
		List<RegisterHelperClass> find = template.find(q, RegisterHelperClass.class);
		ResponseHelper res = new ResponseHelper(true, find);
		return res;
	}

	@Override
	public ResponseHelper getAllTweetsByUsers(String uname) {
		// TODO Auto-generated method stub
		Query q = new Query();
		q.addCriteria(Criteria.where("userEmail").is(uname));
		q.addCriteria(Criteria.where("status").is(0));

		List<TweetsDetails> find = template.find(q, TweetsDetails.class);
		ResponseHelper res = new ResponseHelper(true, find);
		return res;
	}

	@Override
	public ResponseHelper getAllTweets() {
		// TODO Auto-generated method stub

		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(0));
		// query.fields().i

		List<TweetsDetails> find = template.find(query, TweetsDetails.class);
		ResponseHelper res = new ResponseHelper(true, find);
		return res;

	}

	@Override
	public ResponseHelper deleteTweet(String tweetid) {
		// TODO Auto-generated method stub
		ResponseHelper res = new ResponseHelper(false, null);

		Query query = new Query();
		query.addCriteria(Criteria.where("tweetId").is(tweetid));
		TweetsDetails findById = template.findOne(query, TweetsDetails.class);

		Optional<TweetsDetails> ofNullable = Optional.ofNullable(findById);
		if (ofNullable.isPresent()) {
			Update update = new Update();
			update.set("status", 1);
			TweetsDetails findAndModify = template.findAndModify(query, update, TweetsDetails.class);

			res.setStatus(true);
			res.setData(findAndModify);

			return res;

		} else {
			res.setStatus(false);
			res.setData("enter proper tweetid");
			return res;
		}
	}

	@Override
	public ResponseHelper updateTweet(String tweetid, TweetsDetails details) {
		// TODO Auto-generated method stub
		ResponseHelper res = new ResponseHelper(false, null);
		TweetsDetails findById = template.findById(tweetid, TweetsDetails.class);
		Optional<TweetsDetails> ofNullable = Optional.ofNullable(findById);
		if (ofNullable.isPresent()) {
			TweetsDetails tweetsDetails = ofNullable.get();
			tweetsDetails.setMessage(details.getMessage());
			tweetsDetails.setTweetdate(LocalDate.now());
			TweetsDetails save = template.save(tweetsDetails);
			res.setStatus(true);
			res.setData(save);

			return res;

		} else {
			res.setStatus(false);
			res.setData("enter proper tweetid");
			return res;
		}
	}

	@Override
	public ResponseHelper Register(RegisterHelperClass registerHelperClass) {
		// TODO Auto-generated method stub
		Validator validator;
		ResponseHelper res = new ResponseHelper(false, "");
		RegisterHelperClass findById = template.findById(registerHelperClass.getEmail(), RegisterHelperClass.class);
		Optional<RegisterHelperClass> ofNullable = Optional.ofNullable(findById);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		Set<ConstraintViolation<RegisterHelperClass>> violations = validator.validate(registerHelperClass);
		System.out.println(violations + "violationsviolationsviolations" + violations.isEmpty());
		if (violations.size() > 0) {
			res.setStatus(false);
			String message = "";
			for (ConstraintViolation<RegisterHelperClass> s : violations) {
				message = message + s.getMessage() + "   ";
			}
			res.setData(message);

			return res;
		} else {
			if (ofNullable.isPresent()) {
				res.setData("user mailid is already used");
				res.setStatus(false);

			} else {
				RegisterHelperClass save = template.save(registerHelperClass, "RegistrationDetails");
				res.setData(template.findById(save.getEmail(), RegisterHelperClass.class));
				res.setStatus(true);
			}

			return res;
		}

	}

	@Override
	public ResponseHelper getTweetByUsers(String emailid) {
		// TODO Auto-generated method stub
		Query q = new Query();
		q.addCriteria(Criteria.where("userEmail").is(emailid.trim()));
		q.addCriteria(Criteria.where("status").is(0));
		List<TweetsDetails> find = template.find(q, TweetsDetails.class);
		System.out.println(find.toString());
		ResponseHelper res = new ResponseHelper(true, find);
		return res;
	}

	@Override
	public ResponseHelper dislikeTweet(String tweetid, String emailid) {
		// TODO Auto-generated method stub
		ResponseHelper res =new ResponseHelper(false, "");
		TweetsDetails findById = template.findById(tweetid.trim(), TweetsDetails.class);
		Optional<TweetsDetails> ofNullable = Optional.ofNullable(findById);
		if (ofNullable.isPresent()) {
			List<RegisterHelperClass> likedUsersList = findById.getLikedUsersList();
			CopyOnWriteArrayList<RegisterHelperClass> writeArrayList = new CopyOnWriteArrayList<>(likedUsersList);
			System.out.println(writeArrayList + "      before removing liked users");
			if (writeArrayList.size() > 0) {
				for (RegisterHelperClass i : writeArrayList) {

					if (i.getEmail().equals(emailid.trim())) {
						writeArrayList.remove(i);
					}
				}
			}

			List<RegisterHelperClass> updatedArrayListLikedUsers = new ArrayList<RegisterHelperClass>(writeArrayList);

			Query query = new Query();
			query.addCriteria(Criteria.where("tweetId").is(tweetid));
			Update update = new Update();

			update.set("likedUsersList", updatedArrayListLikedUsers);
			TweetsDetails findAndModify = template.findAndModify(query, update, TweetsDetails.class);
			res.setData(findAndModify);
			res.setStatus(true);
			
			return res;

		}
		else
		{
			res.setData("tweetid not found");
			res.setStatus(false);
			
			return res;
		}

	
	}

}