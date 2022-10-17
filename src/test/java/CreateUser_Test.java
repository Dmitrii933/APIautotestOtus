import DTO.User;
import DTO.UserOut;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserNew;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUser_Test {

    private static final String username = "uriu";
    private static final Long id = 3L;

    //Проверка создания пользователя из ответа через RestAssured
    @Test
    public void checkCreateUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(3L)
                .email("otus@ya.com")
                .userStatus(1L)
                .phone("89998889988")
                .lastName("Отусович")
                .firstName("Юрий")
                .username(username)
                .password("12345")
                .build();

        userNew.createUser(user)
                .statusCode(200)
                .body("message", equalTo("3"));

    }
    //Проверка создания пользователя из ответа через Pojo
    @Test
    public void checkPojoCreateUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(3L)
                .email("otus@ya.com")
                .userStatus(1L)
                .phone("89998889988")
                .lastName("Отусович")
                .firstName("Юрий")
                .username(username)
                .password("12345")
                .build();

        UserOut userOut = userNew.createUser(user).extract().body().as(UserOut.class);
        String message = userOut.getMessage();
        Assertions.assertEquals(Long.toString(id), message, "Incorrect Message");
    }

    //Проверка создания пользователя из ответа через jsonPath
    @Test
    public void checkExstractjsonPathCreateUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(3L)
                .email("otus@ya.com")
                .userStatus(1L)
                .phone("89998889988")
                .lastName("Отусович")
                .firstName("Юрий")
                .username(username)
                .password("12345")
                .build();

        String actualMessage = userNew.createUser(user).extract().jsonPath().get("message");
        Assertions.assertEquals(Long.toString(id), actualMessage,"Incorrect message");
    }

    //Проверка создания пользователя из ответа через JsonSchema
    @Test
    public void checkJsonSchemaCreateUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(3L)
                .email("otus@ya.com")
                .userStatus(1L)
                .phone("89998889988")
                .lastName("Отусович")
                .firstName("Юрий")
                .username(username)
                .password("12345")
                .build();

        userNew.createUser(user)
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/CreateUser.json"));

    }

    //Проверка получения пользователя из ответа через RestAssured
    @Test
    public void checkReceiveUser() {
        UserNew userNew = new UserNew();
        userNew.receiveUser(username)
                .statusCode(200)
                .body("username", equalTo("uriu"));
    }

    //Проверка получения пользователя из ответа через JsonSchema
    @Test
    public void checkJsonSchemaReceiveUser() {
        UserNew userNew = new UserNew();
        userNew.receiveUser(username)
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/receiveUser.json"));
    }
}
