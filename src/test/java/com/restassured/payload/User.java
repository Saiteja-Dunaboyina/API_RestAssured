package com.restassured.payload;

import lombok.*;

@Setter
@Getter
@ToString
public class User {
	
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String userStatus;
}
