package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.restassured.base.Base;
import com.restassured.endpoints.UserEndPoints;

import io.restassured.response.Response;

public class APIUserTesting extends Base{
	
	@Test(priority = 1)
	public void creatingUser() {
		System.out.println("Post-----------------------------");
		test = extentReports.createTest("Create User")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 2)
	public void gettingUserByUsername() {
		System.out.println("Get-------------------------------");
		test = extentReports.createTest("Get User By Username")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority = 3)
	public void updateUserByUsername() {
		System.out.println("Put----------------------------------");
		test = extentReports.createTest("Update User By Username")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		faker = new Faker();
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body();
		assertEquals(response.statusCode(), 200);
		Response response2 = UserEndPoints.getUser(this.userPayload.getUsername());
		response2.then().log().all();
		assertEquals(response2.statusCode(), 200);
	}
	
	@Test(priority = 4)
	public void deleteUserByUsername() {
		System.out.println("Delete---------------------------------");
		test = extentReports.createTest("Delete User By Username")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		assertEquals(response.statusCode(),200);
	}

}
