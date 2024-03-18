package com.restassured.endpoints;

public class Routes {
	
	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	public static String postUrl = baseUrl+"/user";
	public static String getByUsername = baseUrl+"/user/{username}";
	public static String updateByUsername = baseUrl+"/user/{username}";
	public static String deleteByUsername = baseUrl+"/user/{username}";
	
	public static String bookingBaseUrl = "https://restful-booker.herokuapp.com";
	
	public static String authPostUrl = bookingBaseUrl + "/auth";
	public static String bookingPostUrl = bookingBaseUrl + "/booking";
	public static String bookingGetUrl = bookingBaseUrl + "/booking/{id}";
	public static String bookingUpdateUrl = bookingBaseUrl + "/booking/{id}";
	public static String bookingDeleteUrl = bookingBaseUrl + "/booking/{id}";
	
	//reqres end points
	
	public static String reqresBaseUrl = "https://reqres.in/api/users";
	
	public static String postUser = reqresBaseUrl; //201
	public static String getAllUsers = reqresBaseUrl; //200
	public static String getUserById = reqresBaseUrl + "/{id}"; //200
	public static String updateUserById = reqresBaseUrl + "/{id}"; //200
	public static String deleteUserById = reqresBaseUrl + "/{id}"; //204
}
