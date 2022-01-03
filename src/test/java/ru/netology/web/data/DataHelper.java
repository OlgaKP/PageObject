package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardsInfo {
        private int sum;
        private String numCard;
    }

    private static final Faker faker = new Faker(new Locale("en"));

    public static CardsInfo getFirstCardsInfo(AuthInfo authInfo) {
        return new CardsInfo(faker.number().numberBetween(0, 10000), "5559 0000 0000 0002");
    }

    public static CardsInfo getSecondCardsInfo(AuthInfo authInfo) {
        return new CardsInfo(faker.number().numberBetween(0, 10000), "5559 0000 0000 0001");
    }
}

