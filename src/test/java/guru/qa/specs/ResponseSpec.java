package guru.qa.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {
    public static ResponseSpecification getLogs200 = getLogs()
            .expectStatusCode(200)
            .build();
    public static ResponseSpecification getLogs201 = getLogs()
            .expectStatusCode(201)
            .build();
    public static ResponseSpecification getLogs204 = getLogs()
            .expectStatusCode(204)
            .build();
    public static ResponseSpecification getLogs404 = getLogs()
            .expectStatusCode(404)
            .build();

    private static ResponseSpecBuilder getLogs() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY);
    }
}
