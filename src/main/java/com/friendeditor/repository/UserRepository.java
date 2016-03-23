package com.friendeditor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.friendeditor.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("from User u where u.fbId = :fbId")
	User findById(@Param("fbId") Long fbId);

}
