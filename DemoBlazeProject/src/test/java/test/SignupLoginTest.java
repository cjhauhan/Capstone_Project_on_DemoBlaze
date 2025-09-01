package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

public class SignupLoginTest extends BaseTest {

    @Test(description = "Sign up a new user and verify login")
    public void signupAndLogin() {
        String uniqueUser = "user" + System.currentTimeMillis();
        String pass = "Pass@123";

        HomePage home = new HomePage(getDriver()).open();
        home.signup(uniqueUser, pass);
        home.login(uniqueUser, pass);

        LoginPage lp = new LoginPage(getDriver());
        Assert.assertTrue(lp.isLoggedIn(), "User should be logged in after signup and login.");
        System.out.println("Successfully done the signup");
    }
}
