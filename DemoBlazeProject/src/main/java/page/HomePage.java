package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import page.components.ModalsPage;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private String baseUrl = "https://www.demoblaze.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
        return this;
    }

    // ----- Existing user's locators & methods -----
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
    private By firstItem = By.cssSelector("#tbodyid .card a[href*='prod.html']");

    public void login(String user, String pass) {
        click(loginBtn);
        type(usernameInput, user);
        type(passwordInput, pass);
        click(loginModalBtn);
        acceptAlertIfPresent(); // for wrong password alert
    }

    public void signup(String user, String pass) {
        click(signupBtn);
        type(signupUser, user);
        type(signupPass, pass);
        click(signupModalBtn);
        acceptAlertIfPresent();
    }

    public boolean isLogoutVisible() {
        return isVisible(logoutLink);
    }

    public void goToPhones() {
        click(phonesCategory);
    }

    public void openFirstItem() {
        click(firstItem);
    }

    // ----- New helpers for richer tests -----
    private By cartLink = By.id("cartur");
    private By nextBtn = By.id("next2");
    private By prevBtn = By.id("prev2");
    private By productCards = By.cssSelector("#tbodyid .card h4 a");

    private ModalsPage modals() { return new ModalsPage(driver); }

    public void goToCart() { click(cartLink); }

    

    public List<String> getVisibleProductNames() {
        return driver.findElements(productCards).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickNext() { click(nextBtn); wait.until(d -> !getVisibleProductNames().isEmpty()); }
    public void clickPrevious() { click(prevBtn); wait.until(d -> !getVisibleProductNames().isEmpty()); }

    public void openProduct(String productName) {
        click(By.xpath("//a[@class='hrefch' and normalize-space()='" + productName + "']"));
    }

    public int getCardPrice(String productName) {
        String txt = driver.findElement(By.xpath(
            "//a[@class='hrefch' and normalize-space()='" + productName + "']" +
            "/ancestor::div[contains(@class,'card')]//h5")).getText();
        return Integer.parseInt(txt.replaceAll("[^0-9]", ""));
    }
    public String loginAndGetAlert(String user, String pass) {
        click(loginBtn);
        if (user != null && !user.isBlank()) type(usernameInput, user);
        if (pass != null && !pass.isBlank()) type(passwordInput, pass);
        click(loginModalBtn);
        org.openqa.selenium.Alert a = wait.until(
            org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
        String t = a.getText();
        a.accept();
        return t;
    }
    

   
   

    // Modal delegates
    public void openSignupModal() { modals().openSignupModal(); }
    public String signupAndGetAlert(String u, String p) { return modals().signupAndGetAlert(u,p); }
    public void openContactModal() { modals().openContactModal(); }
    public void fillContact(String e,String n,String m){ modals().fillContact(e,n,m); }
    public String submitContactAndGetAlert(){ return modals().submitContactAndGetAlert(); }
}
