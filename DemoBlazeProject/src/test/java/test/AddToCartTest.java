package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.ProductPage;
import page.CartPage;
import org.openqa.selenium.By;

public class AddToCartTest extends BaseTest {

    @Test(description = "Add phone and laptop to cart, then verify both exist")
    public void addPhoneAndLaptopToCart() {
        HomePage home = new HomePage(getDriver()).open();

        home.goToPhones();
        home.openFirstItem();
        new ProductPage(getDriver()).addToCart();

        home.open();
        getDriver().findElement(By.linkText("Laptops")).click();
        home.openFirstItem();
        new ProductPage(getDriver()).addToCart();

        CartPage cart = new CartPage(getDriver());
        cart.openCart();
        
        System.out.println("Laptop and phone is added to the cart");

        Assert.assertTrue(cart.isProductInCart(), "Cart should contain both phone and laptop");
    }
}
