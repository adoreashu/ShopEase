package com.shopease.automation.api;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    @BeforeClass
    public void setup() { RestAssured.baseURI = "http://localhost:8080/api"; }

    @Test
    public void testGetProducts() {
        given().when().get("/products").then().statusCode(200).body("size()", greaterThan(0));
    }
    
    @Test
    public void testLoginValid() {
        given().contentType(ContentType.JSON).body("{\"email\":\"john.doe@example.com\", \"password\":\"Password@123\"}")
        .when().post("/auth/login").then().statusCode(200).body("token", notNullValue());
    }
}
