package com.restassured.endpoints;

import com.restassured.payload.BookingDetails;
import com.restassured.payload.TokenCreation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class BookingAPIEndPoints {
	
	public static Response createToken(TokenCreation creation) {
		return given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(creation)
				.when()
				.post(Routes.authPostUrl);
	}
	
	public static Response createBooking(BookingDetails details) {
		return given()
				.log().all()
				.contentType(ContentType.JSON)
				.body(details)
				.when()
				.post(Routes.bookingPostUrl);
	}
	
	public static Response getBookingById(int bookingId) {
		return given()
				.pathParam("id",bookingId)
				.log().all()
				.when()
				.get(Routes.bookingGetUrl);
	}
	
	public static Response updateBookingById(int id,BookingDetails details,String token) {
		return given()
				.contentType(ContentType.JSON)
				.header("Cookie","token="+token)
				.pathParam("id", id)
				.body(details)
				.log().all()
				.when()
				.get(Routes.bookingUpdateUrl);
	}
	
	public static Response deleteBookingById(int id,String token) {
		return given()
				.header("Cookie","token="+token)
				.pathParam("id", id)
				.log().all()
				.when()
				.delete(Routes.bookingDeleteUrl);
				
	}

}
