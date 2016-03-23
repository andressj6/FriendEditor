package com.friendeditor.services;

import org.springframework.data.domain.Page;

import com.friendeditor.model.Friend;
import com.friendeditor.model.User;

public interface UserService {

	User saveUserAndFriends(User user, String token);
	
	void deleteUser(User user);
	
	User findUser(Long fbId);
	
	Page<Friend> findUserFriends(Long fbId);
	
}
