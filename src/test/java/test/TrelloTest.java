package test;

import dataProvider.PropertyManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TrelloTest {

    @BeforeAll

    public static  void setUp(){
        RestAssured.baseURI = PropertyManager.get("baseURI");
        RestAssured.basePath = PropertyManager.get("basePath");
    }
    @Test
    public void  api(){
        String boardId =
                given()
                        .contentType("application/json").
                        when()
                        .queryParam("key", PropertyManager.get("key"))
                        .queryParam("token", PropertyManager.get("token"))
                        .queryParam("name", "TrelloBoard")
                        .post("/boards")
                         .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).
                        assertThat()
                        .body("name", equalTo("TrelloBoard"))
                        .extract().path("id");

        String listId =
                given()
                        .contentType("application/json")
                        .when()
                        .queryParam("key", PropertyManager.get("key"))
                        .queryParam("token", PropertyManager.get("token"))
                        .queryParam("name","TrelloList")
                        .post("/boards/"+boardId+"/lists")
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .assertThat()
                        .body("name", equalTo("TrelloList"))
                        .extract().path("id");

        String[] cardsIdArr = new String[2];


        for (int i = 0; i < 2; i++) {
            cardsIdArr[i] = given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", PropertyManager.get("key"))
                    .queryParam("token", PropertyManager.get("token"))
                    .queryParam("name","newCard"+i)
                    .queryParam("idList",listId)
                    .post("/cards").
                    then()
                    .statusCode(200)
                    .contentType(ContentType.JSON).
                    assertThat()
                    .body("name", equalTo("newCard"+i))
                    .extract().path("id");
        }

        //Edit one of the cards
        Random rn = new Random();
        int a = rn.nextInt(2);
        given()
                .contentType("application/json").
                when()
                .queryParam("key", PropertyManager.get("key"))
                .queryParam("token", PropertyManager.get("token"))
                .queryParam("name","newCardRandom")
                .queryParam("desc","edited Test Description")
                .put("/cards/"+cardsIdArr[a]).
                then()
                .statusCode(200)
                .contentType(ContentType.JSON).
                assertThat()
                .body("desc", equalTo("edited Test Description"))
                .extract().path("id");

        //Delete all of the cards
        for (int i = 0; i < 2; i++) {
            given()
                    .contentType("application/json").
                    when()
                    .queryParam("key", PropertyManager.get("key"))
                    .queryParam("token", PropertyManager.get("token"))
                    .delete("/cards/"+cardsIdArr[i]).
                    then()
                    .statusCode(200);
        }

        //Delete board
        given()
                .contentType("application/json").
                when()
                .queryParam("key", PropertyManager.get("key"))
                .queryParam("token", PropertyManager.get("token"))
                .pathParam("id",boardId)
                .delete("/boards/{id}").
                then()
                .statusCode(200);
    }
}


