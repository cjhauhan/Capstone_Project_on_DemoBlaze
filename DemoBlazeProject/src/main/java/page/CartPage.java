package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {

    private By cartLink = By.id("cartur");
    private By cartRows = By.cssSelector("#tbodyid tr");
    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    // Order modal fields
    private By name = By.id("name");
    private By country = By.id("country");
    private By city = By.id("city");
    private By card = By.id("card");
    private By month = By.id("month");
    private By year = By.id("year");
    private By purchaseBtn = By.xpath("//button[text()='Purchase']");
    private By confirmModal = By.cssSelector(".sweet-alert.showSweetAlert.visible");
    private By confirmOK = By.cssSelector(".confirm.btn.btn-lg.btn-primary");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        click(cartLink);
    }

    public boolean isProductInCart() {
        List<WebElement> rows = driver.findElements(cartRows);
        return rows.size() > 0;
    }

    public void placeOrder(String nm, String ctry, String cty, String cc, String mm, String yy) {
        click(placeOrderBtn);
        type(name, nm);
        type(country, ctry);
        type(city, cty);
        type(card, cc);
        type(month, mm);
        type(year, yy);
        click(purchaseBtn);
    }

    public boolean isConfirmationShown() {
        return isVisible(confirmModal);
    }

    public String getConfirmationText() {
        if (isConfirmationShown()) {
            return driver.findElement(confirmModal).getText();
        }
        return "";
    }

    public void confirmOk() {
        click(confirmOK);
    }
}
