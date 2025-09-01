package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By welcomeUser = By.id("nameofuser");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoggedIn() {
        return isVisible(welcomeUser);
    }

    public String getWelcomeText() {
        if (isLoggedIn()) {
            return driver.findElement(welcomeUser).getText();
        }
        return "";
    }
}
