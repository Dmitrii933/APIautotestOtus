package specification;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specification {
        private RequestSpecification specification;
    private  String BASE_URL = System.getProperty("base.url");
       public RequestSpecification spec() {
           specification = given()
                   .baseUri(BASE_URL)
                   .contentType(ContentType.JSON)
                   .log().all();

           return specification;

       }
    }

