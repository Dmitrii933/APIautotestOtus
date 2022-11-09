import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import dto.User;
import dto.UserOut;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserNew;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUser_Test {

    private Faker faker = new Faker();

    private FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());

    //Проверка создания пользователя
    @Test
    public void checkCreateUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(faker.number().randomNumber())
                .email(fakeValuesService.bothify("?????##@gmail.com"))
                .userStatus(faker.number().randomNumber())
                .phone(fakeValuesService.bothify("+7 9##-###-##-##"))
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .username(faker.name().username())
                .password(faker.gameOfThrones().dragon())
                .build();

       String userNameReq = user.getUsername();
       String userIdReq = Long.toString(user.getId());

        UserOut userOut = userNew.createUser(user)
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);
        String message = userOut.getMessage();
        Assertions.assertEquals(userIdReq, message, "Incorrect Message");


      User userRes = userNew.receiveUser(userNameReq)
                .statusCode(200)
              .extract()
              .body()
              .as(User.class);

      Assertions.assertEquals(user, userRes,"Incorrect user");


    }
//Проверка удаления юзера
    @Test
    public void checkDeleteUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(faker.number().randomNumber())
                .email(fakeValuesService.bothify("?????##@gmail.com"))
                .userStatus(faker.number().randomNumber())
                .phone(fakeValuesService.bothify("+7 9##-###-##-##"))
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .username(faker.name().username())
                .password(faker.gameOfThrones().dragon())
                .build();

        String userNameReq = user.getUsername();
        String userIdReq = Long.toString(user.getId());

        UserOut userOut = userNew.createUser(user)
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);
        String message = userOut.getMessage();
        Assertions.assertEquals(userIdReq, message, "Incorrect Message");


userNew.deleteUser(userNameReq)
                .statusCode(200)
        .body("message", equalTo(userNameReq));

userNew.receiveUser(userNameReq)
        .statusCode(404)
        .body("message",equalTo( "User not found"))
        .body("type", equalTo("error"));

    }
    //Проверка обновления юзера
    @Test
    public void сheckUpdateUser() {
        UserNew userNew = new UserNew();
        User user = User.builder()
                .id(faker.number().randomNumber())
                .email(fakeValuesService.bothify("?????##@gmail.com"))
                .userStatus(faker.number().randomNumber())
                .phone(fakeValuesService.bothify("+7 9##-###-##-##"))
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .username(faker.name().username())
                .password(faker.gameOfThrones().dragon())
                .build();

        String userNameReq = user.getUsername();
        String userIdReq = Long.toString(user.getId());

        UserOut userOut = userNew.createUser(user)
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);
        String message = userOut.getMessage();
        Assertions.assertEquals(userIdReq, message, "Incorrect Message");


        User userUpdate = User.builder()
                .id(faker.number().randomNumber())
                .email(fakeValuesService.bothify("?????##@gmail.com"))
                .userStatus(faker.number().randomNumber())
                .phone(fakeValuesService.bothify("+7 9##-###-##-##"))
                .lastName(faker.name().lastName())
                .firstName(faker.name().firstName())
                .username(faker.name().username())
                .password(faker.gameOfThrones().dragon())
                .build();

        String userIdIUpd = Long.toString(userUpdate.getId());

        userNew.updateUser(userUpdate, userNameReq)
                .statusCode(200)
                .body("message", equalTo(userIdIUpd));

        Assertions.assertNotEquals(user,userUpdate,"User not update");
    }

    //Мне нужно чтобы потом юзать (НЕ ОБРАЩАТЬ ВНИМАНИЕ!)
  /*  //Проверка создания пользователя из ответа через Pojo
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
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));

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
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/receiveUser.json"));
    }*/
}
