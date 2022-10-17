package services;

import DTO.User;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import specification.Specification;

import static io.restassured.RestAssured.given;

public class UserNew {
    private  static final String PATH_USER ="/user/";
    Specification spec = new Specification();
    private final  RequestSpecification specification = spec.spec();


    public ValidatableResponse createUser(User user){

       return given(specification)
       .when()
               .body(user)
               .post(PATH_USER)
       .then()
               .log().all();
    }

    public ValidatableResponse receiveUser(String user){
        return given(specification)
                .when()
                .get(PATH_USER+user)
                .then()
                .log().all();
    }


}
