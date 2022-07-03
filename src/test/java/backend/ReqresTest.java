package backend;

import backend.registration.Register;
import backend.registration.SuccessReg;
import backend.registration.UnSuccessReg;
import backend.spec.Specifications;
import backend.user.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    /**
     * 1. Получить список пользователей со второй страница на сайте https://reqres.in/
     * 2. Убедиться что id пользователей содержаться в их avatar;
     * 3. Убедиться, что email пользователей имеет окончание reqres.in;
     */
    @Test
    @DisplayName("Аватары содержат ID пользователей")
    public void checkUserAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseOK200());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

    /**
     * 1. Используя сервис https://reqres.in/ протестировать регистрацию.
     * a. Убедиться, что регистрация успешна.
     * b. Убедиться, что при отсутствии пароля регистрация не успешна.
     * Проверять коды ошибок.
     */
    @Test
    @DisplayName("Успешная регистрация")
    public void successUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    @DisplayName("Не успешная регистрация")
    public void unSuccessUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseError400());

        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        Assertions.assertEquals("Missing password", unSuccessReg.getError());
    }
}
