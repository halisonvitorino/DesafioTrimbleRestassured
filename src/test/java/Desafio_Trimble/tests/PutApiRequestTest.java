package Desafio_Trimble.tests;

import Desafio_Trimble.utils.BaseTest;
import Desafio_Trimble.utils.FileNamesConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static Desafio_Trimble.utils.FileNamesConstants.BASE_URL;

public class PutApiRequestTest extends BaseTest {
    @Test
    public void changeItem() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postApiBody      = objectMapper.readTree(new File(FileNamesConstants.PUT_API_BODY));

        Response responsePost =
                RestAssured
                        .given()
                            .contentType(ContentType.JSON)
                            .body(postApiBody)
                            .baseUri(BASE_URL)
                        .when()
                            .post("anything/1")
                        .then()
                            .assertThat()
                            .statusCode(200)
                            .extract().response();
        
        JsonNode responseBody = objectMapper.readTree(responsePost.body().asString());
        Assert.assertEquals(responseBody.get("json").get("todo").toString(), postApiBody.get("todo").toString());
    }
    @Test
    public void createItemNotAllowedMethod() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode postApiBody = objectMapper.readTree(new File(FileNamesConstants.PUT_API_BODY));

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(postApiBody)
                    .baseUri(BASE_URL)
                .when()
                    .post("anything/")
                .then()
                    .assertThat()
                    .statusCode(404)
                    .statusLine("HTTP/1.1 404 NOT FOUND");
    }
}
