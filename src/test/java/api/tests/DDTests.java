package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class DDTests {
	
	User userPayload;
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=api.utilities.Dataproviders.class)
	public void testPostUsers(String userid, String userName, String firstName, String lastName, String email, String pasword, String phone) {
		
		userPayload = new User();
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(userName);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(pasword);
		userPayload.setPhone(phone);
		
		Response res = UserEndPoints.createUser(this.userPayload);
		System.out.println(userName);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		
	}
	
	@Test(priority=2,dependsOnMethods = {"testPostUsers"}, dataProvider="UserNames", dataProviderClass=api.utilities.Dataproviders.class)
	public void testGetUser(String userName) {
		
		Response res = UserEndPoints.readUser(userName);
		System.out.println(userName);
		res.then().log().all(); 
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		
	}
	
	
	@Test(priority=3, dependsOnMethods = {"testPostUsers"}, dataProvider="UserNames", dataProviderClass=api.utilities.Dataproviders.class)
	public void testDeleteUser(String userName) {
		
		Response res = UserEndPoints.deleteUser(userName);
		System.out.println(userName);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		//System.out.println(res.jsonPath().getString("message"));
		
	}
	

}
