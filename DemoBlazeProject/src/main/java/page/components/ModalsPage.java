package page.components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.BasePage;

public class ModalsPage extends BasePage {

    public ModalsPage(WebDriver driver) { super(driver); }

    // Signup
    private By signupNav = By.id("signin2");
    private By signupUser = By.id("sign-username");
    private By signupPass = By.id("sign-password");
    private By signupBtn  = By.xpath("//button[text()='Sign up']");

    // Contact
    private By contactNav = By.id("contact2");
    private By contactEmail = By.id("recipient-email");
    private By contactName  = By.id("recipient-name");
    private By contactMsg   = By.id("message-text");
    private By contactSend  = By.xpath("//div[@id='exampleModal']//button[text()='Send message']");

    public void openSignupModal() {
        click(signupNav);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupUser));
    }

    public String signupAndGetAlert(String user, String pass) {
        type(signupUser, user);
        type(signupPass, pass);
        click(signupBtn);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        String t = a.getText(); a.accept(); return t;
    }

    public void openContactModal() {
        click(contactNav);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmail));
    }

    public void fillContact(String email, String name, String msg) {
        type(contactEmail, email);
        type(contactName, name);
        type(contactMsg, msg);
    }

    public String submitContactAndGetAlert() {
        click(contactSend);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        String t = a.getText(); a.accept(); return t;
    }
}