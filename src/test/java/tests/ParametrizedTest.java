package tests;

import apiClient.ApiClientUser;
import io.restassured.response.Response;
import models.User;
import models.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static testdata.TestData.DEFAULT_USER;
import static testdata.TestData.INVALID_USER;

/**
 * Параметризованные тесты делают, чтобы один и тот же тест запускался с разными наборами данных — без дублирования кода.
 * 🧩 Зачем:
 * уменьшить количество одинаковых тестов;
 * проверить поведение системы на разных входных данных;
 * повысить покрытие и читаемость тестов.
 */
public class ParametrizedTest {
    ApiClientUser apiClientUser = new ApiClientUser();
    UserResponse userResponse = new UserResponse();


    static Stream<User> users() {
        return Stream.of(DEFAULT_USER, INVALID_USER);
    }

    @ParameterizedTest
    @MethodSource ("users")

    public void createUserParametrizedTest(User users) {
        Response response = apiClientUser.createUser(users);
        UserResponse createdUserResponse = response.as(UserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());


    }
}
