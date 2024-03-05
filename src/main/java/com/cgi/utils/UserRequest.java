package com.cgi.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
	private String mail;
	private String password;

	public String getMail() {
		return mail;
	}
	
	public String getPassword() {
		return password;
	}
}
