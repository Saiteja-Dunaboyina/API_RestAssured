package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.restassured.base.Base;
import com.restassured.endpoints.ReqResAPIEndPoints;

import io.restassured.response.Response;

public class ReqResAPITesting extends Base{
	
	String userId;
	int id;
	
	@Test(priority = 1)
	public void creatingUser() {
		test = extentReports.createTest("Creating User")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = ReqResAPIEndPoints.createUser(employee);
		userId =  response.then()
					.log().all()
					.statusCode(201)
					.extract()
					.path("id");
		id = Integer.parseInt(userId);
		assertEquals(response.getStatusCode(), 201);
	}
	
	@Test(priority = 2)
	public void gettingAllUsers() {
		test = extentReports.createTest("Getting All Users")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = ReqResAPIEndPoints.gettingAllUsers(2);
		response.then()
			.log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void gettingUserByUserId() {
		test = extentReports.createTest("Getting User by User Id")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = ReqResAPIEndPoints.gettingUserById(2);
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void updateUserByUserId() {
		test = extentReports.createTest("Update the user by using user id")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		employee.setName(faker.name().name());
		employee.setJob(faker.job().position());
		Response response = ReqResAPIEndPoints.updateUserById(id, employee);
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 5)
	public void deleteUserByUserId() {
		test = extentReports.createTest("Delete User by using user id")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = ReqResAPIEndPoints.deleteUserById(id);
		response.then().log().all();
		assertEquals(response.getStatusCode(), 204);
	}

}
