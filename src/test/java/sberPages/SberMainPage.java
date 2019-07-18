package sberPages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberMainPage extends BasePage {
    public final static String SBER_URL = "http://www.sberbank.ru/ru/person";

    @FindBy(xpath = "//*[@aria-label='Меню Страхование']/span")
    private WebElement insuranceButton;

    @FindBy(xpath = "//*[@id='submenu-5']//*[contains(text(), 'Путешествия и покупки')]")
    private WebElement travelAndShoppingButton;


    @Step
    public SberTravelAndShoppingPage openSberTravelAndShoppingPage() {
        waitForReadyElement(insuranceButton).click();
        waitForReadyElement(travelAndShoppingButton).click();
        return new SberTravelAndShoppingPage();
    }
}
