package sberPages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberTravelAndShoppingPage extends BasePage {

    @FindBy(xpath = "//h3[contains(text(),'Страхование путешественников')]")
    private WebElement travelInsuranceTitle;

    @FindBy(xpath = "//*[@data-pid='SBRF-TEXT-2247407']//a[contains(text(), 'Оформить онлайн')]")
    private WebElement makeOnlineButton;

    @Step("check title to contains - \"Страхование путешественников\"")
    public void checkTitle() {
        checkTextAvailabilityFromElement(travelInsuranceTitle, "Страхование путешественников");
    }

    @Step(" open SberTravelInsuracncePage")
    public SberTravelInsuracncePage openFormInsurance() {
        waitForReadyElement(makeOnlineButton);
        switchWindowByXpath(makeOnlineButton);
        return new SberTravelInsuracncePage();
    }
}
