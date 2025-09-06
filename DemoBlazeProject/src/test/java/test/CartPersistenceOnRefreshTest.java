package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class CartPersistenceOnRefreshTest extends BaseTest {

    @Test
    public void cartItemsPersistAfterRefresh() throws InterruptedException {
        HomePage home = new HomePage(getDriver()).open();
        String product = "Sony vaio i5";

        home.openProduct(product);
        new ProductPage(getDriver()).addToCartAndAccept();

        home.open();
        home.goToCart();
        CartPage cart = new CartPage(getDriver());
        cart.waitForCartToLoad();
        Assert.assertTrue(cart.isItemPresent(product));

        // Refresh
        getDriver().navigate().refresh();
        cart.waitForCartToLoad();
        Assert.assertTrue(cart.isItemPresent(product), "Item should persist after refresh.");
        Thread.sleep(3000);
    }
}
