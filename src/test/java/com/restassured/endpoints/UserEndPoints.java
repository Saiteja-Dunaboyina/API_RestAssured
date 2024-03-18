package com.restassured.endpoints;

import com.restassured.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;


public class UserEndPoints {
	
	public static Response createUser(User payload) {
		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.postUrl);
	}
	
	public static Response getUser(String username) {
		return given()
				.pathParam("username", username)
				.when()
				.get(Routes.getByUsername);
	}
	
	public static Response updateUser(String username,User payload) {
		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
				.when()
				.put(Routes.updateByUsername);
	}
	
	public static Response deleteUser(String username) {
		return given()
				.pathParam("username", username)
				.when()
				.delete(Routes.deleteByUsername);
	}
	
	

}
