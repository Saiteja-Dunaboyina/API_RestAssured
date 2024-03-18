package com.restassured.endpoints;

import static io.restassured.RestAssured.*;

import com.restassured.payload.Employee;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResAPIEndPoints {
	
	public static Response createUser(Employee employee) {
		return given()
				.contentType(ContentType.JSON)
				.body(employee)
				.when()
				.post(Routes.postUser);
	}
	
	public static Response gettingAllUsers(int pageno) {
		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.param("page", pageno)
				.when()
				.get(Routes.getAllUsers);
	}
	
	public static Response gettingUserById(int id) {
		return given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.get(Routes.getUserById);
	}
	
	public static Response updateUserById(int id,Employee employee) {
		return given()
				.contentType(ContentType.JSON)
				.body(employee)
				.pathParam("id", id)
				.when()
				.put(Routes.updateUserById);
	}
	
	public static Response deleteUserById(int id) {
		return given()
				.contentType(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.delete(Routes.deleteUserById);
	}
	
	

}
