package com.techproed.tests.smoketest;

import com.techproed.pages.DefaultPage;
import com.techproed.pages.LoginPage;
import com.techproed.pages.MainPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day12_PositiveLoginTest {

    @Test
    public void positiveLoginTest(){

        Driver.getDriver().get(ConfigReader.getProperty("app_url"));

        //1. create page objects-MainPage, LoginPage - DONE
        /*2. create test class
        -Create Page Objects
            -create MainPage object
            -calling the page elements using that object
        */
        MainPage mainPage = new MainPage();
        mainPage.mainPageLoginLink.click();

        //At this point we are on the LoginPage
        //Create LoginPage object
        LoginPage loginPage = new LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();

        //We need to automate the login success
        //We choose a CORE ELEMENT on the page
        //We will use Add User button to verify the login page
        //At this point, we are on the Default Page

        DefaultPage defaultPage = new DefaultPage();
        boolean isLoggedIn = defaultPage.addUserButton.isDisplayed();
        Assert.assertTrue(isLoggedIn);
    }
}
