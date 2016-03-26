package com.friendeditor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.friendeditor.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("from Friend f where f.user.fbId = :fbId")
    List<Friend> findFriendsByUser(@Param("fbId") Long userId);

    @Query("delete from Friend f where f.user.fbId = :fbId")
    @Modifying
    void deleteAllFromUser(@Param("fbId") Long fbId);

}
