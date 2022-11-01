package specification;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specification {
        private RequestSpecification specification;

       public RequestSpecification spec(String BASE_URL) {
           return specification = given()
                   .baseUri(BASE_URL)
                   .contentType(ContentType.JSON)
                   .log().all();

       }
    }

