package guru.qa.test;

import guru.qa.modals.Lombok.NewUserBodyLombokModel;
import guru.qa.modals.Lombok.NewUserResponseLombokModel;
import guru.qa.modals.POJO.RegisterBodyPOJOModel;
import guru.qa.modals.POJO.RegisterResponsePOJOModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.specs.RequestSpecs.*;
import static guru.qa.specs.ResponseSpec.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class RestAPITest {

    @DisplayName("Проверка фамилии пользователя с 5 id")
    @Test
    void successGetUser() {
        given()
                .spec(allureAndUri)
                .when()
                .get("users/5")
                .then()
                .spec(getLogs200)
                .body("data.last_name", is("Morris"));
    }

    @DisplayName("Проверка о ненахождении сайта")
    @Test
    void checkSingleUser404Error() {
        given()
                .spec(allureAndUri)
                .when()
                .get("users/404")
                .then()
                .spec(getLogs404);
    }

    @DisplayName("Создание новго пользователя и проверка его имени")
    @Test
    void createUserTest() {
        String name = "Denis";

        NewUserBodyLombokModel newUser = new NewUserBodyLombokModel();
        newUser.setName(name);
        newUser.setJob("Idiot");

        NewUserResponseLombokModel response = given()
                .spec(allureAndUriWithJson)
                .body(newUser)
                .when()
                .post("users")
                .then()
                .spec(getLogs201)
                .extract()
                .as(NewUserResponseLombokModel.class);

        assertThat(response.getName()).isEqualTo(name);
    }

    @DisplayName("Удаление пользователя")
    @Test
    void deleteUserTest() {
        given()
                .spec(allureAndUri)
                .when()
                .delete("register")
                .then()
                .spec(getLogs204);
    }

    @DisplayName("Тест на регистрацию")
    @Test
    void registryTest() {
        RegisterBodyPOJOModel register = new RegisterBodyPOJOModel();
        register.setEmail("eve.holt@reqres.in");
        register.setPassword("pistol");

        RegisterResponsePOJOModel response = given()
                .spec(allureAndUriWithJson)
                .body(register)
                .when()
                .post("register")
                .then()
                .spec(getLogs200)
                .extract()
                .as(RegisterResponsePOJOModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @DisplayName("Тест на пантон-цвета")
    @Test
    void pantoneTest(){
        given()
                .spec(allureAndUri)
                .when()
                .get("/unknown")
                .then()
                .spec(getLogs200)
                .body("data.findAll{it.pantone_value =~/^17-/}.pantone_value.flatten()", hasItem("17-1456"));
    }
}
