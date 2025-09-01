package test;

import org.testng.annotations.Test;

import page.HomePage;

public class WrongPasswordTest extends BaseTest {

    @Test(description = "Login with wrong password and close browser")
    public void loginWithWrongPassword() throws InterruptedException {
        String user = "testuser";    // Change to an existing username if needed
        String wrongPass = "Wrong@123";

        HomePage home = new HomePage(getDriver()).open();
        home.login(user, wrongPass);
        System.out.println("Checked the popup for wrong password");
        System.out.println("If any user enter the wrong credentials");

        // Wait briefly for alert
        Thread.sleep(2000);


        // Quit the browser after handling alert
        getDriver().quit();
    }
}
