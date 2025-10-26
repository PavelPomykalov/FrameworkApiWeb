package api.testdata;

import com.github.javafaker.Faker;
import api.models.User;

public class TestData {
    public static Faker faker =  new Faker();

    public static final User DEFAULT_USER = User.builder()
            .id(faker.number().numberBetween(1,99999))
            .username(faker.name().name())
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .password(faker.internet().password())
            .email(faker.internet().emailAddress())
            .phone(faker.numerify("2342342342"))
            .userStatus(faker.number().numberBetween(1,3))
            .build();

    public static final User INVALID_USER = User.builder()
            .build();


}
