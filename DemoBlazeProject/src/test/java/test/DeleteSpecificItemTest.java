package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class DeleteSpecificItemTest extends BaseTest {

	@Test
	public void deletingOneItemKeepsOtherAndUpdatesTotal() {
		HomePage home = new HomePage(getDriver()).open();

		// Add first product A
		String A = "Samsung galaxy s6";
		home.openProduct(A);
		ProductPage pdp = new ProductPage(getDriver());
		int priceA = pdp.getPrice();
		pdp.addToCartAndAccept();

		// Add second product B
		home = new HomePage(getDriver()).open();
		String B = "Nokia lumia 1520";
		home.openProduct(B);
		pdp = new ProductPage(getDriver());
		int priceB = pdp.getPrice();
		pdp.addToCartAndAccept();

		// Go to cart
		home = new HomePage(getDriver()).open();
		home.goToCart();
		CartPage cart = new CartPage(getDriver());
		cart.waitForCartToLoad();

		// Both present
		Assert.assertTrue(cart.isItemPresent(A), "Expected item A in cart");
		Assert.assertTrue(cart.isItemPresent(B), "Expected item B in cart");

		// Delete A

		cart.deleteItem(A);

		// If A still shows or total hasn’t adjusted, refresh once
		if (cart.isItemPresent(A) || cart.getTotalSafe() != priceB) {
			cart.refreshCart();
		}

		Assert.assertFalse(cart.isItemPresent(A));
		Assert.assertTrue(cart.isItemPresent(B));
		Assert.assertEquals(cart.getTotalSafe(), priceB);

	}
}
