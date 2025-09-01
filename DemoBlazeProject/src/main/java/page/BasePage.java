package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected WebElement $(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement el = $(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected boolean isVisible(By locator) {
        try {
            return $(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void acceptAlertIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.alertIsPresent());
            Alert a = driver.switchTo().alert();
            a.accept();
        } catch (TimeoutException te) {
            // ignore
        }
    }
}
