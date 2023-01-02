package backend;

import appleInsider.URL;
import backend.registration.Register;
import backend.registration.SuccessReg;
import backend.registration.UnSuccessReg;
import backend.spec.Specifications;
import backend.storage.USER;
import backend.user.ColorsData;
import backend.user.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static backend.storage.API.*;
import static backend.storage.API.BASEURL;
import static io.restassured.RestAssured.given;

public class ReqresTest {
    @Test
    @DisplayName("Аватары содержат ID пользователей")
    public void checkUserAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASEURL.getApi()), Specifications.responseOK200());
        List<UserData> users = given()
                .when()
                .get(USERS.getApi()+"?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));
        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASEURL.getApi()), Specifications.responseOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register(USER.EMAIL.getUserData(), USER.PASSWORD.getUserData());
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post(REGISTER.getApi())
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    @DisplayName("Не успешная регистрация")
    public void unSuccessUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(BASEURL.getApi()), Specifications.responseError400());

        Register user = new Register(USER.BAD_EMAIL.getUserData(), "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .post(REGISTER.getApi())
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        Assertions.assertEquals("Missing password", unSuccessReg.getError());
    }


    @Test
    @DisplayName("Список цветов предоставляется отсортированным по годам")
    public void sortedColorsYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(BASEURL.getApi()), Specifications.responseOK200());
        List<ColorsData> colors = given()
                .when()
                .get(COLORS.getApi())
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colors.stream().map(ColorsData::getYear).toList();
        List<Integer> sortedYears = years.stream().sorted().toList();
        Assertions.assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);
    }
}
