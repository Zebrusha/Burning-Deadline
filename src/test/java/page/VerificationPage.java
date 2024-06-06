package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code]");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notivication] .notification__content");


    public void veryfiVeryficationPageVisiblity(){
        codeField.shouldBe(Condition.visible);
    }

    public void veryfiErrorNotification(String expectedText){
        errorNotification.shouldHave(Condition.attribute(expectedText)).shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(String verificationCode){
        verify(verificationCode);
        return new DashboardPage();
    }

    public void verify(String verifycationCode){
        codeField.setValue(verifycationCode);
        verifyButton.click();
    }


}
