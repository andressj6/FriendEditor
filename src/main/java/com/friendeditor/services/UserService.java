package com.friendeditor.services;

import java.util.List;

import com.friendeditor.model.Friend;
import com.friendeditor.model.User;

public interface UserService {

	User saveUserAndFriends(User user, String token);
	
	void deleteUser(Long fbId);
	
	User findUser(Long fbId);
	
	List<Friend> findUserFriends(Long fbId);
	
}
