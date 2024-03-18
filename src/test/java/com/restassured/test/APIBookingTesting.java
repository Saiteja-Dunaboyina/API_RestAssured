package com.restassured.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.restassured.base.Base;
import com.restassured.endpoints.BookingAPIEndPoints;
import com.restassured.utils.FileConstant;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class APIBookingTesting extends Base{
	
	int bookingId;
	String token;
	
	@Test(priority = 1)
	public void getToken() {
		System.out.println("Create token "+ "=========================================  ");
		test = extentReports.createTest("Generate Token")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = BookingAPIEndPoints.createToken(tokenCreation);
		token = response.then().log().all()
				.statusCode(200)
				.extract()
				.path("token");
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void createBooking() throws IOException {
		System.out.println("Create Booking "+ "=========================================  ");
		test = extentReports.createTest("Create Booking")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		System.out.println(bookingDetails);
		Response response  = BookingAPIEndPoints.createBooking(bookingDetails);
		String jsonSchema = FileUtils.readFileToString(new File(FileConstant.JSONSCHEMA_PATH),"UTF-8");
		response.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
		bookingId = response.then().log().all()
				.statusCode(200)
				.extract()
				.path("bookingid");
		System.out.println(bookingId+"=======================================");
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void getBooking() {
		System.out.println("Get Booking "+ "=========================================  ");
		test = extentReports.createTest("Getting the Booking details by Booking Id")
				.assignAuthor("Sai Teja").assignDevice("Window");
		Response response = BookingAPIEndPoints.getBookingById(bookingId);
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority = 4)
	public void updateBooking() {
		System.out.println("Update Booking "+ "=========================================  ");
		test = extentReports.createTest("Update Booking details")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		bookingDetails.setFirstname(faker.name().firstName());
		bookingDetails.setLastname(faker.name().lastName());
		Response response = BookingAPIEndPoints.updateBookingById(bookingId, bookingDetails, token);
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 5)
	public void deleteBooking() {
		System.out.println("Delete Booking "+ "=========================================  ");
		test = extentReports.createTest("Delete Booking by using booking id")
				.assignAuthor("Sai Teja").assignDevice("Windows");
		Response response = BookingAPIEndPoints.deleteBookingById(bookingId, token);
		response.then().log().all();
		assertEquals(response.getStatusCode(), 201);
	}
	
	

}
