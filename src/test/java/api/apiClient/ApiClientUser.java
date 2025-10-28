package api.apiClient;

import config.TestPropertiesConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import api.models.User;
import org.aeonbits.owner.ConfigFactory;

import static api.constans.Constans.BASE_URI;
import static api.constans.Constans.USER_ENDPOINT;

public class ApiClientUser {
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class,System.getProperties());

    private RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .baseUri(configProperties.getApiBaseUrl())
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
