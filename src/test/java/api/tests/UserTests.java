package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker fk;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		fk = new Faker();
		userPayload = new User();
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().emailAddress());
		userPayload.setPassword(fk.internet().password(5, 9, true, true, true));
		userPayload.setPhone(fk.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
				
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("------Creating user-------");
		Response res = UserEndPoints.createUser(this.userPayload);
		System.out.println(userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		logger.warn("------User created properly-------");
	}
	
	@Test(priority=2, dependsOnMethods = { "testPostUser" })
	public void testGetUser() {
		
		logger.info("------Getting user-------");
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		System.out.println(userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		logger.debug("------Get user ok-------");
		
	}
	
	@Test(priority=3, dependsOnMethods = { "testPostUser" })
	public void testPutUser() {
		
		logger.info("------Updating user-------");
		//update some payload data
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().emailAddress());
		
		Response res = UserEndPoints.updateUser(this.userPayload.getUsername(), this.userPayload);
		System.out.println(userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		
		//check data after update
		Response resAfter = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(resAfter.getStatusCode(),200);
		logger.info("------Checking user-------");
	}
	
	@Test(priority=4, dependsOnMethods = { "testPostUser" })
	public void testDeleteUser() {
		logger.info("------Deleting user-------");
		
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		System.out.println(userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		logger.error("------Deleting completed-------");
		
	}
	

}
