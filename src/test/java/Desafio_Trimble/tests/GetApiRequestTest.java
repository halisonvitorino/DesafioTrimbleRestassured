package Desafio_Trimble.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import Desafio_Trimble.utils.BaseTest;

import static Desafio_Trimble.utils.FileNamesConstants.BASE_URL;

public class GetApiRequestTest extends BaseTest {
    @Test
    public void decodeStringSuccess(){
        String encodedString = "SFRUUEJJTiBpcyBhd2Vzb21l";
        Response response =
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri(BASE_URL)
                .when()
                    .get("base64/" + encodedString)
                .then()
                    .assertThat()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                .extract()
                    .response();
        String responseDecodedString = response.getBody().asString();
        String decodedString         = decodeString(encodedString);
        Assert.assertEquals(responseDecodedString, decodedString);
    }

    @Test
    public void decodeStringInvalid(){
        Response response =
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .baseUri(BASE_URL)
                .when()
                    .get("base64/invalid")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .extract().response();
        String message = response.asString();
        Assert.assertEquals(message, "Incorrect Base64 data try: SFRUUEJJTiBpcyBhd2Vzb21l");
    }
    @Test()
    public void decodeStringNonExistent(){
        RestAssured
                .given()
                    .baseUri(BASE_URL)
                .when()
                    .get("base64/")
                .then()
                    .assertThat()
                    .statusCode(404)
                    .statusLine("HTTP/1.1 404 NOT FOUND");
    }
}
