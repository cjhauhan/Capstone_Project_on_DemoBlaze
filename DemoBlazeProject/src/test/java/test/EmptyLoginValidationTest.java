package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class EmptyLoginValidationTest extends BaseTest {

    @Test
    public void loginWithEmptyFieldsShowsAlert() {
        HomePage home = new HomePage(getDriver()).open();

        String alert = home.loginAndGetAlert("", "");
        Assert.assertTrue(alert != null && !alert.isBlank(),
                "Expected a validation alert when logging in with empty fields.");
    }
}
