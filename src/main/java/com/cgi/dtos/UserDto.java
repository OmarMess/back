package com.cgi.dtos;

import com.cgi.model.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDto {
	private Long id;
	private String mail;
	@Enumerated(EnumType.STRING)
	private Role role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
