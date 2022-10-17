package specification;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specification {
        private RequestSpecification specification;

        private  static final String BASE_URL="https://petstore.swagger.io/v2";
       public RequestSpecification spec() {
           return specification = given()
                   .baseUri(BASE_URL)
                   .contentType(ContentType.JSON)
                   .log().all();

       }
    }

