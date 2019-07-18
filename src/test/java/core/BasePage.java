package core;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public BasePage() {
        this.driver = Init.getDriver();
        webDriverWait = new WebDriverWait(driver, 50, 200);
        PageFactory.initElements(driver, this);
    }

    @Step("Wait for ready {element}")
    public WebElement waitForReadyElement(WebElement element) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Step("Wait for ready and click for js{element}")
    public void waitForReadyAndClickElmnt(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    @Attachment("Screenshot")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("filling in the form {name} with the entered values {textTo}")
    public void fillInputByName(String name, String textTo, String and) {
        String temp = "//*[text() = '%s']/following::input[1]";
        String fullXpath = String.format((temp), name) + and;
        WebElement element = driver.findElement(By.xpath(fullXpath));
        waitForReadyElement(element);
        element.sendKeys(textTo);
        String actualText = element.getAttribute("value");
        if (actualText.contains("+7")) {
            waitForReadyElement(element);
            Assert.assertEquals(actualText, "+7 " + textTo);
        } else checkErrorWithAttribute(element, textTo);
    }

    @Step("Validation of {element} attribute with {textTo}")
    public void checkErrorWithAttribute(WebElement element, String textTo) {
        waitForReadyElement(element);
        String actualText = element.getAttribute("value");
        Assert.assertEquals(textTo, actualText);
    }

    @Step("check request text {textTo} from {element}")
    public void checkTextAvailabilityFromElement(WebElement element, String textTo) {
        waitForReadyElement(element);
        Assert.assertTrue(element.getText().contains(textTo));
    }

    @Step
    public void setDate(String xpath, String day, String month, String year) {
        waitForReadyElement(driver.findElement(By.xpath(xpath))).click();
        Select selectYear = new Select(driver.findElement(By.xpath("//*[@class=\"ui-datepicker-year\"]")));
        selectYear.selectByValue(year);
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")).click();
        driver.findElement(By.xpath("//*[@class=\"ui-datepicker-month\"]/option[" + month + "]")).click();
        driver.findElement(By.xpath("//*[@data-event=\"click\"]//*[text()='" + day + "']")).click();
    }

    @Step
    public void switchWindowByXpath(WebElement element) {
        Set<String> oldWindowsSet = driver.getWindowHandles();
        waitForReadyAndClickElmnt(element);
//      findElementXpath(stringXpath).click();
        String newWindowHandle = (new WebDriverWait(driver, 10))
                .until((ExpectedCondition<String>) driver -> {
                            Set<String> newWindowsSet = driver.getWindowHandles();
                            newWindowsSet.removeAll(oldWindowsSet);
                            return newWindowsSet.size() > 0 ?
                                    newWindowsSet.iterator().next() : null;
                        }
                );
        driver.switchTo().window(newWindowHandle);
    }
}
