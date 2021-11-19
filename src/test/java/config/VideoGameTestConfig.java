package config;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class VideoGameTestConfig{
    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;
    @BeforeClass
    public static void setup(){
        // RestAssured.proxy("localhost",8866); //fiddler
        
        requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setBasePath("/app/")
            .setPort(8080)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
        
            RestAssured.requestSpecification = requestSpecification; 
        RestAssured.responseSpecification = responseSpecification;    
    }
}