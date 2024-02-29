package com.cgi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserApp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String mail;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role ;
	
}
