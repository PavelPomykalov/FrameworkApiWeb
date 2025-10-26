package tests;

import apiClient.ApiClientUser;
import io.restassured.response.Response;
import models.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static testdata.TestData.DEFAULT_USER;
import static testdata.TestData.INVALID_USER;


public class SmokeApiTests {
    @Test
    public void createUser(){

        ApiClientUser apiClientUser = new ApiClientUser();
        Response response = apiClientUser.createUser(DEFAULT_USER);
        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertEquals("unknown", userResponse.getType());
    }

    @Test
    public void createUserInvalid(){

        ApiClientUser apiClientUser = new ApiClientUser();
        Response response = apiClientUser.createUser(INVALID_USER);
        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertEquals("unknown", userResponse.getType());
    }
}
