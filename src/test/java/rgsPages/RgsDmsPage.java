package rgsPages;

import core.BasePage;
import core.User;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RgsDmsPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'добровольное медицинское страхование')]")
    private WebElement dmcTitle;

    @FindBy(xpath = "//a[contains(text(),'Отправить заявку')]")
    private WebElement sendRequestButton;

    @FindBy(xpath = "//*[contains(text(),'Заявка на добровольное медицинское страхование')]")
    private WebElement textRequest;

    @FindBy(xpath = "//*[@id='applicationForm']/div[2]/div[8]/textarea")
    private WebElement commentField;

    @FindBy(xpath = "//select[@name='Region']")
    private WebElement selectRegion;

    @FindBy(xpath = "//*[contains(text(),'Я согласен')]/preceding::input[@class='checkbox']")
    private WebElement checkbox;

    @FindBy(xpath = "//*[@id= 'button-m']")
    private WebElement sendButton;

    @FindBy(xpath = "//*[contains(text(),'Эл. почта')]/following::span[contains(@class, 'validation')]")
    private WebElement emailValidation;

    @Step("check title - \"добровольное медицинское страхование\"")
    public void checkTitle() {
        checkTextAvailabilityFromElement(dmcTitle, "добровольное медицинское страхование");
    }

    @Step("click for button after waiting")
    public void clickSendFormButton() {
        waitForReadyElement(sendRequestButton).click();
    }

    @Step("check request text - \"Заявка на добровольное медицинское страхование\"")
    public void checkTextAvailability() {
        checkTextAvailabilityFromElement(textRequest, "Заявка на добровольное медицинское страхование");
    }


    @Step("filling the form with user data {user}")
    public void fillInTheForm(User user) {
        fillInputByName("Фамилия", user.getLastName(), "");
        fillInputByName("Имя", user.getFirstName(), "");
        fillInputByName("Отчество", user.getPatronymic(), "");
        fillInputByName("Телефон", user.getTelephoneNumber(), "");
        fillInputByName("Эл. почта", user.getEmail(), "");

        commentField.sendKeys("здесь был Selenium");
        checkErrorWithAttribute(commentField, "здесь был Selenium");

        new Select(selectRegion).selectByValue("77");
        checkErrorWithAttribute(selectRegion, "77");
        checkbox.click();
    }

    @Step("click button send")
    public void sendForm() {
        waitForReadyElement(sendButton).click();
    }

    @Step("check e-mail error")
    public void checkEmailError() {
        checkTextAvailabilityFromElement(emailValidation, "Введите адрес электронной почты");
        takeScreenshot();
    }


}
