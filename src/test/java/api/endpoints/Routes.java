package api.endpoints;

/**
PetStore
User Operations
POST - https://petstore.swagger.io/v2/user - Create user
GET - https://petstore.swagger.io/v2/user/{username} - Get user by user name
PUT - https://petstore.swagger.io/v2/user/{username} - Updated user
DELETE - https://petstore.swagger.io/v2/user/{username} - Delete user
*/
public class Routes {
	
	public static String base_URL = "https://petstore.swagger.io/v2";
	
	//User endpoints
	public static String post_url = base_URL+"/user";
	public static String get_url = base_URL+"/user/{username}";
	public static String put_url = base_URL+"/user/{username}";
	public static String delete_url = base_URL+"/user/{username}";

}
