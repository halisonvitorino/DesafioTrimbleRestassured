package Desafio_Trimble.tests;

import Desafio_Trimble.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static Desafio_Trimble.utils.FileNamesConstants.BASE_URL;

public class DelApiRequestTest extends BaseTest {
    @Test
    public void deleteItem() throws IOException {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri(BASE_URL)
                .when()
                    .post("anything/1")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract().response();
    }
    @Test
    public void createItemNotAllowedMethod() throws IOException {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri(BASE_URL)
                .when()
                    .post("anything/")
                .then()
                    .assertThat()
                    .statusCode(404)
                    .statusLine("HTTP/1.1 404 NOT FOUND");
    }
}
