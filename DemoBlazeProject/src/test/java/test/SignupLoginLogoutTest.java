package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class SignupLoginLogoutTest extends BaseTest {

    @Test(description = "Sign up a unique user, login and then logout (stable - doesn't require pre-created user)")
    public void signupLoginAndLogout() throws InterruptedException {
        // create a unique username so signup won't collide
        String uniqueUser = "user" + System.currentTimeMillis();
        String password = "Pass@123";

        // open home and sign up
        HomePage home = new HomePage(getDriver()).open();
        home.signup(uniqueUser, password);

        // after signup, immediately try to login with same credentials
        home.login(uniqueUser, password);

        // verify login succeeded by checking welcome text
        LoginPage lp = new LoginPage(getDriver());
        Assert.assertTrue(lp.isLoggedIn(), "User should be logged in after signup+login.");

        // logout and verify logout is not visible anymore
        getDriver().findElement(By.id("logout2")).click();
        Assert.assertFalse(home.isLogoutVisible(), "Logout should disappear after signing out");
        System.out.println("Checked login and logout");
    }
}
