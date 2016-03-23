package com.friendeditor.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.friendeditor.model.Friend;
import com.friendeditor.model.User;
import com.friendeditor.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	@Transactional
	public User saveUserAndFriends(User user, String token) {
		getFacebookTemplate(token).userOperations().getUserProfile();
		return user;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUser(Long fbId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Friend> findUserFriends(Long fbId) {
		// TODO Auto-generated method stub
		return null;
	}

	private Facebook getFacebookTemplate(String token) {
		return new FacebookTemplate(token);
	}

}
