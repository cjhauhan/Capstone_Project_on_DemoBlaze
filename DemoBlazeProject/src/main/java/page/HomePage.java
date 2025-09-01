package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By loginBtn = By.id("login2");
    private By usernameInput = By.id("loginusername");
    private By passwordInput = By.id("loginpassword");
    private By loginModalBtn = By.xpath("//button[text()='Log in']");
    private By logoutLink = By.id("logout2");

    private By signupBtn = By.id("signin2");
    private By signupUser = By.id("sign-username");
    private By signupPass = By.id("sign-password");
    private By signupModalBtn = By.xpath("//button[text()='Sign up']");

    private By phonesCategory = By.linkText("Phones");
    private By firstItem = By.cssSelector("#tbodyid .card-title a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.demoblaze.com/");
        return this;
    }

    public boolean isLogoutVisible() {
        return isVisible(logoutLink);
    }

    public void openLoginModal() {
        click(loginBtn);
    }

    public void login(String user, String pass) {
        openLoginModal();
        type(usernameInput, user);
        type(passwordInput, pass);
        click(loginModalBtn);
        // give time for login to process
        acceptAlertIfPresent(); // if invalid creds, might show alert
    }

    public void openSignupModal() {
        click(signupBtn);
    }

    public void signup(String user, String pass) {
        openSignupModal();
        type(signupUser, user);
        type(signupPass, pass);
        click(signupModalBtn);
        acceptAlertIfPresent();
    }

    public void goToPhones() {
        click(phonesCategory);
    }

    public void openFirstItem() {
        click(firstItem);
    }
}
