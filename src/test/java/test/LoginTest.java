package test;

import data.DataHelper;
import data.SQLHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    LoginPage loginPage;

    @AfterEach
    void tearDown(){
        SQLHelper.cleanAuthCodes();
    }
    @AfterEach
    static void tearDownAll(){
        SQLHelper.cleanDatabase();
    }

    @BeforeEach
    void setUp(){
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    void shouldSuccessfulLogin(){
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verifycationPage = loginPage.validLogin(authInfo);
        verifycationPage.veryfiVeryficationPageVisiblity();
        var veryficatonCode = SQLHelper.getVerificationCode();
        verifycationPage.validVerify(veryficatonCode.getCode());
    }

    @Test
    void shouldGetErrorNotificationsIfLoginWithRandomUserWithoutAddingToBase(){
        var authInfo = DataHelper.generateRandomeUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotification("Ошибка \nНеверно указан логин или пароль");
    }

    @Test
    void shouldGetErrorNotificationIfLoginWithExistUserAndRandomVerificationPage(){
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verifycationPage = loginPage.validLogin(authInfo);
        verifycationPage.veryfiVeryficationPageVisiblity();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verifycationPage.verify(verificationCode.getCode());
        verifycationPage.veryfiErrorNotification("Ошибка \nНеверно указан код! Попробуйте еще раз.");
    }


}
