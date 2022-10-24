package test;


import dataProvider.PropertyManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class apiTest {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = PropertyManager.get("baseURI");
        RestAssured.basePath = PropertyManager.get("basePath");
    }

    @DisplayName("TestiniumTrelloApiTask")
    @Test
    public void CreateBoardCardsPostEditAndDelete(){
        String boardId =
                given()
                        .contentType("application/json").
                        when()
                        .queryParam("key", PropertyManager.get("key"))
                        .queryParam("token", PropertyManager.get("token"))
                        .queryParam("name", "TrelloBoard")
                        .post("/boards").
                        then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).
                        assertThat()
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
