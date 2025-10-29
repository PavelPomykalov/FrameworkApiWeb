package api.tests;

import api.apiClient.ApiClientUser;
import io.restassured.response.Response;
import api.models.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static api.testdata.TestData.DEFAULT_USER;
import static api.testdata.TestData.INVALID_USER;


public class SmokeApiTests {
    @Tag("API")
    @Test
    public void createUser(){

        ApiClientUser apiClientUser = new ApiClientUser();
        Response response = apiClientUser.createUser(DEFAULT_USER);
        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertEquals("unknown", userResponse.getType());
    }

    @Tag("API")
    @Tag("SMOKE")
    @Test
    public void createUserInvalid(){

        ApiClientUser apiClientUser = new ApiClientUser();
        Response response = apiClientUser.createUser(INVALID_USER);
        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertEquals("unknown", userResponse.getType());
    }
}
