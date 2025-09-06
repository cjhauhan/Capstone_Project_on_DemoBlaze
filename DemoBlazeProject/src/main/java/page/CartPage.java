package page;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    // Confirmation SweetAlert
    private By confirmModal = By.cssSelector(".sweet-alert.showSweetAlert.visible");
    private By confirmText = By.cssSelector(".sweet-alert.showSweetAlert.visible p");
    private By confirmOK = By.xpath("//button[text()='OK']");

    // Totals
    private By total = By.id("totalp");

    public CartPage(WebDriver driver) { super(driver); }

    // ===== Helpers =====
    public void waitForCartToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartRows));
    }

    public int getItemCount() {
        return driver.findElements(cartRows).size();
    }

    public String getTotalText() {
        try { return driver.findElement(total).getText(); }
        catch (Exception e) { return ""; }
    }

    public int getTotalSafe() {
        try {
            String txt = getTotalText();
            if (txt == null || txt.isBlank()) return 0;
            return Integer.parseInt(txt.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public void refreshCart() {
        driver.navigate().refresh();
        waitForCartToLoad();
    }

    // ===== Existing features =====
    public void openCart() { click(cartLink); }

    public boolean isProductInCart() {
        List<WebElement> rows = driver.findElements(cartRows);
        return rows != null && !rows.isEmpty();
    }

    public void placeOrder(String n, String ctry, String cty, String crd, String mm, String yy) {
        click(placeOrderBtn);
        type(name, n);
        type(country, ctry);
        type(city, cty);
        type(card, crd);
        type(month, mm);
        type(year, yy);
        click(purchaseBtn);
    }

    public boolean isConfirmationShown() {
        return isVisible(confirmModal);
    }

    public String getConfirmationText() {
        if (isConfirmationShown()) {
            return driver.findElement(confirmText).getText();
        }
        return "";
    }

    public void confirmOk() { click(confirmOK); }

    // ===== Item operations =====
    public boolean isItemPresent(String productName) {
        return !driver.findElements(By.xpath("//tr/td[text()='" + productName + "']")).isEmpty();
    }

    public void deleteItem(String productName) {
        int before = getItemCount();
        String oldTotal = getTotalText();

        click(By.xpath("//tr[td[text()='" + productName + "']]//a[text()='Delete']"));

        // Wait only for row count to drop by 1 (reliable)
        wait.until(d -> getItemCount() == Math.max(0, before - 1));

        // Give the total a short chance to change (1.5s max)
        long end = System.currentTimeMillis() + 1500;
        while (System.currentTimeMillis() < end) {
            if (!getTotalText().equals(oldTotal)) break;
            try { Thread.sleep(120); } catch (InterruptedException ignored) {}
        }
    }

  
    public void clickPlaceOrder() { click(placeOrderBtn); }

    public void fillOrderForm(String n, String ctry, String cty, String crd, String mm, String yy) {
        if(!n.isEmpty()) type(name, n);
        if(!ctry.isEmpty()) type(country, ctry);
        if(!cty.isEmpty()) type(city, cty);
        if(!crd.isEmpty()) type(card, crd);
        if(!mm.isEmpty()) type(month, mm);
        if(!yy.isEmpty()) type(year, yy);
    }

    public String submitOrderAndGetAlert() {
        click(purchaseBtn);
        Alert a = wait.until(ExpectedConditions.alertIsPresent());
        String t = a.getText(); a.accept(); return t;
    }

    public String confirmPurchaseAndGetReceipt() {
        click(purchaseBtn);
        By receipt = By.cssSelector(".sweet-alert.showSweetAlert.visible p");
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(receipt)).getText();
        click(confirmOK);
        return text;
    }
}
