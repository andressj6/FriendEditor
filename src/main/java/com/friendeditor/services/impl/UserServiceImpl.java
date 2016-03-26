package com.friendeditor.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import com.friendeditor.model.Friend;
import com.friendeditor.model.User;
import com.friendeditor.repository.FriendRepository;
import com.friendeditor.repository.UserRepository;
import com.friendeditor.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FriendRepository friendRepo;

    @Override
    public User saveUserAndFriends(User user, String token) {
        Facebook facebook = getFacebookTemplate(token);
        user = saveUserInformation(user, facebook);
        saveUserFriendsInformation(user, facebook);
        return user;
    }

    @Transactional
    private void saveUserFriendsInformation(User user, Facebook facebook) {
        PagedList<org.springframework.social.facebook.api.User> friendList = facebook.friendOperations()
                .getFriendProfiles();
        try {
            while (!friendList.isEmpty()) {
                friendList.stream().forEach(friend -> {
                    Friend f = new Friend(friend.getName(), Long.parseLong(friend.getId()), user);
                    friendRepo.save(f);
                });
                friendList = facebook.friendOperations().getFriendProfiles(friendList.getNextPage());
            }
        } catch (DataIntegrityViolationException ex) {
            // Friend already exists for current user
            ex.printStackTrace();
        }
    }

    @Transactional
    private User saveUserInformation(User user, Facebook facebook) {
        org.springframework.social.facebook.api.User userProfile = facebook.userOperations().getUserProfile();
        user.setName(userProfile.getName());
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        try {
            friendRepo.deleteAllFromUser(userId);
            userRepo.delete(userId);
        } catch (DataIntegrityViolationException ex) {
            // non-existent id
            throw new RestClientException("Cannot Delete User data", ex);
        }
    }

    @Override
    public User findUser(Long fbId) {
        return userRepo.findById(fbId); // #findOne
    }

    @Override
    public List<Friend> findUserFriends(Long fbId) {
        return friendRepo.findFriendsByUser(fbId);
    }

    private Facebook getFacebookTemplate(String token) {
        return new FacebookTemplate(token);
    }

}
