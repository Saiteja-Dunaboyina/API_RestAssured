package com.restassured.base;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.restassured.payload.BookingDates;
import com.restassured.payload.BookingDetails;
import com.restassured.payload.Employee;
import com.restassured.payload.TokenCreation;
import com.restassured.payload.User;

import io.restassured.RestAssured;


public class Base {
	
	public static final Logger logger = LogManager.getLogger(Base.class.getName());
	public Faker faker;
	public User userPayload;
	public BookingDetails bookingDetails;
	public TokenCreation tokenCreation;
	public Employee employee;
	public ExtentReports extentReports;
	public ExtentTest test;
    
	@BeforeTest
    public void setUp() {
        logger.info("Report Setup in each Test");
        ExtentSparkReporter spark = new ExtentSparkReporter("./reports/Extentreport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");
        spark.config().setReportName("Test Report");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
	
	@BeforeClass
	public void setupData() throws JsonProcessingException {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(8, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		bookingDetails = new BookingDetails();
		bookingDetails.setFirstname(faker.name().firstName());
		bookingDetails.setLastname(faker.name().lastName());
		bookingDetails.setTotalPrice(faker.number().randomDigit());
		bookingDetails.setDepositPaid(faker.bool().bool());
		bookingDetails.setBookingDates(new BookingDates("2024-02-22", "2024-02-29"));
		bookingDetails.setAdditionalNeeds(faker.lorem().sentence(5));
		System.out.println("from Base class ===========  ");
		tokenCreation  = new TokenCreation();
		tokenCreation.setUsername("admin");
		tokenCreation.setPassword("password123");
		employee = new Employee();
		employee.setName(faker.name().name());
		employee.setJob(faker.job().position());
		System.out.println(employee);
	}
	
	@BeforeMethod
    public void beforeMethod() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Throwable throwable = result.getThrowable();
            StringWriter error = new StringWriter();
            throwable.printStackTrace(new PrintWriter(error));
            logger.error(error);
        }
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        logger.info("Report Result in each Test");
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getThrowable());
            test.log(Status.FAIL, "Test Case Failed");
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed Sucessfully");
        } else {
            test.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterTest
    public void endReport() {
        extentReports.flush();
    }
}
