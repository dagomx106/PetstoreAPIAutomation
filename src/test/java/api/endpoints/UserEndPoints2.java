package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//CRUD requests

public class UserEndPoints2 {
	
	 static ResourceBundle getURL() {
		ResourceBundle route = ResourceBundle.getBundle("routes");
		return route;
	}
	

	public static Response createUser(User payload) {
		
		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(payload)

				.when()
				.post(getURL().getString("post_url"));

		return response;
	}

	public static Response readUser(String username) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("username", username)

				.when()
				.get(getURL().getString("get_url"));

		return response;
	}

	public static Response updateUser(String username, User payload) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(getURL().getString("put_url"));

		return response;
	}

	public static Response deleteUser(String username) {

		Response response = given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.pathParam("username", username)

				.when()
				.delete(getURL().getString("delete_url"));

		return response;
	}



}