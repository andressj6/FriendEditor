/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.friendeditor.rest;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.friendeditor.model.Friend;
import com.friendeditor.model.User;
import com.friendeditor.services.UserService;

/**
 *
 * @author lima_
 */
@RestController
@RequestMapping("/users")
public class UserEndpoints {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userFbId}/friends", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Friend>> getFriendsByUser(@PathVariable String userFbId) {
		return ResponseEntity.ok(userService.findUserFriends(Long.parseLong(userFbId)));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUserAndFriends(@RequestBody Map<String, String> params) {
		try {
			User user = new User();
			user.setFbId(Long.parseLong(params.get("userId")));
			userService.saveUserAndFriends(user, params.get("accessToken").toString());
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{userFbId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserAndFriends(@PathVariable Long userFbId) {
		userService.deleteUser(userFbId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
