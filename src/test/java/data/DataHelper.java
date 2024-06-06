package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper(){}

    public static AuthInfo getAuthInfoWithTestData(){
        return new AuthInfo("vasya","qwerty123");
    }
    private static String generateRandomeLogin(){
        return FAKER.name().username();
    }

    private static String generateRandomePassword(){
        return FAKER.internet().password();
    }

    public static AuthInfo generateRandomeUser(){
        return new AuthInfo(generateRandomeLogin(),generateRandomePassword());
    }

    public static VerificationCode generateRandomVerificationCode(){
        return new VerificationCode(FAKER.numerify("######"));
    }

    @Value
    public static class AuthInfo{
        String login;
        String password;
    }
    @Value
    public static class VerificationCode{
        String code;
    }
}
