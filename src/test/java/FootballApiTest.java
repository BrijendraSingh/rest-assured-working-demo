import org.testng.annotations.Test;

import config.FootballApiTestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class FootballApiTest extends FootballApiTestConfig {
    @Test
    public void getDetailsOfOneArea(){
        given().
                queryParam("areas", 2072).
        when().
                get("areas");        

    }

    @Test
    public void getDateFounded(){
        given().
        when().
                get("teams/57").
        then().
                body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName(){
        given().
        when().
                get("competitions/2021/teams").
        then().
                body("teams.name[0]", equalTo("Arsenal FC"));
    }

    @Test
    public void getAllTeamData(){
        String data = get("teams/57").asString();
        System.out.println(data);
    }

    @Test
    public void getAllTeamData_DoCheckFirst(){
       Response response = given().
                           when().
                                  get("teams/57").
                           then().
                                  contentType(ContentType.JSON). 
                                  body("founded", equalTo(1886)).   
                                  extract().response();
        String data = response.asString();
        System.out.println(data);                                      
    }

    @Test
    public void extractHeaders(){
       Response response = given().
                           when().
                                  get("teams/57").
                           then().
                                  contentType(ContentType.JSON). 
                                  body("founded", equalTo(1886)).   
                                  extract().response();
        Headers headers = response.getHeaders();
        String singleHeader = response.getHeader("X-Authenticated-Client");

        String data = response.asString();
        System.out.println("data: "+ data);                                      
        System.out.println("headers: "+headers);
        System.out.println("singleHeader: "+singleHeader);
    }

    @Test
    public void getAllTeamNames(){
       Response response = given().
                           when().
                                  get("competitions/2021/teams").
                           then(). 
                                  extract().response();
        List<String> teams = response.path("teams.name");
        for(String teamName : teams){
            System.out.println(teamName);
        }                                  
    }
    
}
