/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.friendeditor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author lima_
 */
@Entity
@Table(name = "USER_FRIENDS", uniqueConstraints = { @UniqueConstraint(columnNames = { "FB_ID", "USER_ID" }) })
@SequenceGenerator(name = "USER_FRIENDS_SEQ", initialValue = 1, sequenceName = "USER_FRIENDS_SEQ")
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6506169633772482415L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "USER_FRIENDS_SEQ")
	private Long id;

	@Column(name = "FB_ID")
	private Long fbId;

	@Column(name = "NAME")
	private String name;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public Friend() {
		// TODO Auto-generated constructor stub
	}

	public Friend(String name, Long fbId, User user) {
		this.name = name;
		this.fbId = fbId;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFbId() {
		return fbId;
	}

	public void setFbId(Long fbId) {
		this.fbId = fbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
