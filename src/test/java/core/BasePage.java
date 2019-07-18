package core;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public BasePage() {
        this.driver = Init.getDriver();
        webDriverWait = new WebDriverWait(driver, 50, 200);
        PageFactory.initElements(driver, this);
    }

    @Step("Wait for raady {element}")
    public WebElement waitForReadyElm(WebElement element) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Wait for raady and click {element}")
    public void waitForReadyAndClickElmnt(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
    }



    @Attachment
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step
    public void fillInputByName(String name, String textTo, String and) {
        String temp = "//*[text() = '%s']/following::input[1]";
        String fullXpath = String.format((temp), name)+and;
        WebElement element = driver.findElement(By.xpath(fullXpath));
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElementValue(element, textTo));
        element.sendKeys(textTo);
        checkErrorWithAttribute(element, textTo);
    }

    @Step
    public void checkErrorWithAttribute(WebElement element, String textTo) {
        String actualText = element.getAttribute("value");
        Assert.assertEquals(textTo, actualText);
    }
}
