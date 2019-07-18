package rgsPages;

import core.BasePage;
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

    @FindBy(xpath = "//a[contains(text(),'Отправить заявку')]")
    private WebElement sendButton;

    @FindBy(xpath = "//*[contains(text(),'Эл. почта')]/following::span[contains(@class, 'validation')]")
    private WebElement emailValidation;

    @Step
    public void checkTitle() {
        Assert.assertTrue(dmcTitle.getText().contains("добровольное медицинское страхование"));
    }

    @Step
    public void checkTextAvailability() {
        Assert.assertTrue(textRequest.getText().contains("Заявка на добровольное медицинское страхование"));
    }

    @Step
    public void clickSendFormButton() {
        waitForReadyElm(sendRequestButton).click();
    }

    @Step
    public void fillInTheForm() {
        fillInputByName("Фамилия", "Жуков", "");
        fillInputByName("Имя", "Михаил", "");
        fillInputByName("Отчество", "Сергеевич", "");
        fillInputByName("Телефон", "(913) 145-65-89", "");
        fillInputByName("Эл. почта", "qwertyqwerty", "");
        fillInputByName("Фамилия", "Жуков", "");

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
