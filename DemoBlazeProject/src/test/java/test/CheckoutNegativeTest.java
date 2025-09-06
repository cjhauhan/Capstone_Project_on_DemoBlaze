package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class CheckoutNegativeTest extends BaseTest {

    @Test
    public void purchaseWithMissingFieldsShowsValidation() throws InterruptedException {
        HomePage home = new HomePage(getDriver()).open();

        home.openProduct("Samsung galaxy s6");
        new ProductPage(getDriver()).addToCartAndAccept();

        home = new HomePage(getDriver()).open();
        home.goToCart();

        CartPage cart = new CartPage(getDriver());
        cart.clickPlaceOrder();

        // Fill only name, leave card empty to trigger validation
        cart.fillOrderForm("", "", "", "", "", "");
        String alertText = cart.submitOrderAndGetAlert(); // native alert text
        Assert.assertTrue(alertText.toLowerCase().contains("fill")
                        || alertText.toLowerCase().contains("required")
                        || alertText.toLowerCase().contains("complete"),
                "Expected validation message, got: " + alertText);
        Thread.sleep(2000);
    }
}