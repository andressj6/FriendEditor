/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.friendeditor.rest;

import com.friendeditor.model.Friend;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lima_
 */
@RestController
@RequestMapping("/users")
public class Endpoints {

    @RequestMapping(value = "/{userFbId}/friends", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Friend>> getFriendsByUser(@PathParam("userFbId") Long userId) {
        System.out.println("Teste");
        return ResponseEntity.ok(new ArrayList<Friend>());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUserAndFriends(@RequestBody Map<String, String> params) {
        try {
            //Save
            return ResponseEntity.created(new URI("/{userFbId}/friends")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete/{userFbId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUserAndFriends(@PathParam("userFbId") Long userId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
