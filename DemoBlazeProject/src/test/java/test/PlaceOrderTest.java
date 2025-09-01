package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.ProductPage;
import page.CartPage;

public class PlaceOrderTest extends BaseTest {

    @Test(description = "Place order and verify confirmation")
    public void placeOrderSuccessfully() {
        HomePage home = new HomePage(getDriver()).open();
        home.goToPhones();
        home.openFirstItem();
        new ProductPage(getDriver()).addToCart();

        CartPage cart = new CartPage(getDriver());
        cart.openCart();
        Assert.assertTrue(cart.isProductInCart(), "Cart should have product before placing order");

        cart.placeOrder("John Doe", "India", "Delhi", "4111111111111111", "08", "2027");
        Assert.assertTrue(cart.isConfirmationShown(), "Confirmation should be displayed");
        String text = cart.getConfirmationText();
        Assert.assertTrue(text.contains("Id") && text.contains("Amount"), "Confirmation should include order details");
        cart.confirmOk();
        System.out.println("Order placed successfully");
    }
}
