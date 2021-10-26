package com.techproed.tests;

import com.techproed.pages.TestAddressLoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.annotations.Test;

public class Day_12_TestAddressLoginTest {

    @Test
    public void testAddressLogin() {

        //Going to the application page
        Driver.getDriver().get(ConfigReader.getProperty("test_address_url"));
        //Creating page object
        TestAddressLoginPage testAddressLoginPage = new TestAddressLoginPage();

        //Signing in
        testAddressLoginPage.email.sendKeys(ConfigReader.getProperty("test_address_email"));
        testAddressLoginPage.password.sendKeys(ConfigReader.getProperty("test_address_password"));
        testAddressLoginPage.signInButton.click();
        //closing the driver
        Driver.closeDriver();

        //There 100 test case and you used pass word in all 100 tests
        //and password changes
        //What do you do to fix your automation code?
        //Without config.properties  : go all 100 tests and change the passwords 100 times
        //With config.properties file: go to configuration.properties file and change the value of password




    }
}
