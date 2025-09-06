package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

    private By addToCartBtn = By.linkText("Add to cart");

    private By title = By.cssSelector(".name");
    private By price = By.cssSelector(".price-container");     // "$360 *includes tax"
    private By description = By.cssSelector("#more-information, #tbodyid p");
    private By homeNav = By.linkText("Home"); // fallback: brand By.id("nava")

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // existing test support
    public void addToCart() {
        click(addToCartBtn);
        acceptAlertIfPresent();
    }

    // richer api
    public void addToCartAndAccept() {
        click(addToCartBtn);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        a.accept();
    }

    public String getTitle() { return getText(title); }
    public int getPrice() { return Integer.parseInt(getText(price).replaceAll("[^0-9]", "")); }
    public String getDescription() { return getText(description); }
    public boolean isLoaded() { return isVisible(addToCartBtn); }
    public void clickHome() { click(homeNav); }
}