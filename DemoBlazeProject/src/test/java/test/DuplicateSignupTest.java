package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class DuplicateSignupTest extends BaseTest {

    @Test
    public void signupWithExistingUsernameShowsError() {
        HomePage home = new HomePage(getDriver()).open();
        String user = "dup" + System.currentTimeMillis();
        String pass = "Pass@123";

        // First signup should succeed
        home.openSignupModal();
        String first = home.signupAndGetAlert(user, pass);
        // Second signup should fail with duplicate
        home.openSignupModal();
        String second = home.signupAndGetAlert(user, pass);

        Assert.assertTrue(second.toLowerCase().contains("already") || second.toLowerCase().contains("exist"),
                "Expected duplicate user message, got: " + second + " (first: " + first + ")");
    }
}