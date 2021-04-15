import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test01_GET {

	@Test
	void testRESTAPI() {
		Response response = get("http://dummy.restapiexample.com/api/v1/employees");
		System.out.println(response.asString());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	//using RestAssured - Get Employee details
	@Test
	void getEmployeeDetails() {
		
		given().get("http://dummy.restapiexample.com/api/v1/employees").then().statusCode(200);
	}
	
	//using RestAssured - Deleting employee id#24
	@Test
	void deleteEmployeerecord() {
		given().delete("http://dummy.restapiexample.com/api/v1/employee/24").then().statusCode(200);
	}
	
	// Return Specific Employee (employee id =2) details
	@Test
	void getSpecificEmployeeDetails()
	{
		given().headers("content-Type",ContentType.JSON,"Accept",ContentType.JSON).
		when().get("http://dummy.restapiexample.com/api/v1/employees/6").then().contentType(ContentType.JSON).extract().response();
	}
	
	//Delete" "message" validating response
	@Test
	void deleteSpecificemployeeInfo() {
		String endpoint = "http://dummy.restapiexample.com/api/v1/employee/4";
	
		
		Response response = given().headers("Content-Type",ContentType.JSON,"Accept",ContentType.JSON).
		when().delete(endpoint).then().contentType(ContentType.JSON).extract().response();
		
		String message = response.jsonPath().getString("message");
		
		Assert.assertEquals(message, "Successfully deleted Records");
			
	}
	
	
	
	
	
	
	
	
}
