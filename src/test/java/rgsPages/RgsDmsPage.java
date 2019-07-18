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

    @Step
    public void checkTitle() {
        waitForReadyElm(dmcTitle);
        Assert.assertTrue(dmcTitle.getText().contains("добровольное медицинское страхование"));
    }

    @Step
    public void clickSendFormButton() {
        waitForReadyElm(sendRequestButton).click();
    }

    @Step
    public void checkTextAvailability() {
        waitForReadyElm(textRequest);
        Assert.assertTrue(textRequest.getText().contains("Заявка на добровольное медицинское страхование"));
    }

    @Step
    public void fillInTheForm(User user) {
        fillInputByName("Фамилия", user.getLastName(), "");
        fillInputByName("Имя", user.getFirstName(), "");
        fillInputByName("Отчество", user.getPatronymic(), "");
        fillInputByName("Телефон", user.getTelephoneNumber(), "");
        fillInputByName("Эл. почта", user.getEmail(), "");

        commentField.sendKeys("тут был Selenium");
        checkErrorWithAttribute(commentField, "тут был Selenium");

        new Select(selectRegion).selectByValue("77");
        checkErrorWithAttribute(selectRegion, "77");
        checkbox.click();
    }

    @Step
    public void sendForm() {
        waitForReadyElm(sendButton).click();
    }

    @Step
    public void checkEmailError() {
        waitForReadyElm(emailValidation);
        Assert.assertEquals(emailValidation.getText(), "Введите адрес электронной почты");
    }


}
