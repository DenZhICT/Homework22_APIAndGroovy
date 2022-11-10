package guru.qa.specs;

import guru.qa.helpers.CustomApiListener;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {
    public static RequestSpecification allureAndUri = with()
            .filter(CustomApiListener.withCustomTemplates())
            .log().all()
            .baseUri("https://reqres.in/")
            .basePath("api/");
    public static RequestSpecification allureAndUriWithJson = with()
            .filter(CustomApiListener.withCustomTemplates())
            .log().all()
            .baseUri("https://reqres.in/")
            .basePath("api/")
            .contentType(JSON);
}
