package com.friendeditor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.friendeditor.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {

	
	@Query("from Friend f where f.user.fbId = :fbId")
	Page<Friend> findFriendsByUser(@Param("fbId") Long userId, Pageable pageable);

}
