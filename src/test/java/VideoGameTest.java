import org.testng.annotations.Test;

import config.EndPoints;
import config.VideoGameTestConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;

public class VideoGameTest extends VideoGameTestConfig{

    @Test
    public void getAllVideoGames(){
        given().
        when().
            get(EndPoints.ALL_VIDEO_GAMES).
        then();
    }

    @Test
    public void testVideoGameSerializationByJson(){
        VideoGame videoGame = new VideoGame("99", "2018-04-01", "My Name - Brijendra", "Mature", 15, "Shooter");
        given().
                body(videoGame).
        when().
                post(EndPoints.ALL_VIDEO_GAMES)  .
        then();              

    }

    @Test
    public void getSingleVideoGame(){
        given().
            pathParam("videoGameId", 2).
        when().
            get(EndPoints.SINGLE_VIDEO_GAME).
        then();            
    }

    @Test
    public void testVideoGameSchemaJson(){
        given().
                pathParam("videoGameId", 5).
        when().
                get(EndPoints.SINGLE_VIDEO_GAME).
        then().
                body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));        
    }

    @Test
    public void convertJSONtoPOJO(){
        Response response = given().
                                    pathParam("videoGameId", 5).
                            when().
                                    get(EndPoints.SINGLE_VIDEO_GAME);
        VideoGame vg = response.getBody().as(VideoGame.class);
        System.out.println(vg.toString());     
        System.out.println(vg.getName());                                       

    }
}