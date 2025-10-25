package apiClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import static constans.Constans.BASE_URI;
import static constans.Constans.USER_ENDPOINT;

public class ApiClientUser {

    private RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .accept("application/json")
                .contentType("application/json");
    }

    public Response createUser(User user) {
        return getRequestSpec()
                .log().all()
                .body(user)
                .post(USER_ENDPOINT)
                .then()
                .log().all()
                .statusCode(200) // Ожидаемый статус код
                .extract() // Распаковка того, что пришло в response
                .response();
    }

    public Response updateUser(User user) {
        return getRequestSpec()
                .log().all()
                .body(user)
                .put(USER_ENDPOINT + "/" + user.getUsername())
                .then()
                .log().all()
                .statusCode(200) // Ожидаемый статус код
                .extract() // Распаковка того, что пришло в response
                .response();
    }

    public Response getUserByUsername(String username) {
        return getRequestSpec()
                .log().all()
                .get(USER_ENDPOINT + "/" + username)
                .then()
                .log().all()
                .statusCode(200) // Ожидаемый статус код
                .extract() // Распаковка того, что пришло в response
                .response();
    }


    public Response deleteUserByUsername(String username) {
        return getRequestSpec()
                .log().all()
                .delete(USER_ENDPOINT + "/" + username)
                .then()
                .log().all()
                .statusCode(200) // Ожидаемый статус код
                .extract() // Распаковка того, что пришло в response
                .response();
    }

}
