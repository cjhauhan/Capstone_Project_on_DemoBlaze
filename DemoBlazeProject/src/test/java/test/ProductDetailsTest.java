package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void productTitlePriceAndDescriptionAreVisible() {
        String product = "Samsung galaxy s6";
        HomePage home = new HomePage(getDriver()).open();
        home.openProduct(product);

        ProductPage pdp = new ProductPage(getDriver());
        Assert.assertEquals(pdp.getTitle(), product, "Title must match.");
        Assert.assertTrue(pdp.getPrice() > 0, "Price must be > 0.");
        Assert.assertFalse(pdp.getDescription().isBlank(), "Description must not be empty.");
    }
}