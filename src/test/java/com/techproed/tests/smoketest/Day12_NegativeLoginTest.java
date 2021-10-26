package com.techproed.tests.smoketest;

import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day12_NegativeLoginTest {

    @Test
    public void invalidPassword() {

        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
        LoginPage loginPage = new LoginPage();
        //        When user enters wrong password only
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));//right id
        loginPage.password.sendKeys(ConfigReader.getProperty("wrong_manager_password"));//wrong pass
        loginPage.loginButton.click();

        //        Then verify the error message includes “Wrong password”
        String actualErrorMessage = loginPage.errorMessage.getText();
        System.out.println(actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains("Wrong password"));

        Driver.closeDriver();
        //DONE

    }

    @Test
    public void invalidID(){
//        invalidID()
//        When user enters wrong id only
        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
        LoginPage loginPage = new LoginPage();

        loginPage.username.sendKeys(ConfigReader.getProperty("wrong_manager_username"));//wrong id

        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();


//        Then verify the error message includes “Try again please”
        String actualErrorMessage = loginPage.errorMessage.getText();
        System.out.println(actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains("Try again please"));

        Driver.closeDriver();
//        Test Data:
//        Url: http://www.carettahotel.com/Account/Logon
//        user: manager123
//        pw: Manager1!
    }

    @Test
    public void invalidIDandPassword(){
//        invalidIDAndPassword()
        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
        LoginPage loginPage = new LoginPage();

        loginPage.username.sendKeys(ConfigReader.getProperty("wrong_manager_username"));//wrong id
        loginPage.password.sendKeys(ConfigReader.getProperty("wrong_manager_password"));//wrong password
        loginPage.loginButton.click();

//        Then verify the error message includes “Username or password is incorrect, please correct them and try again”
        String actualErrorMessage = loginPage.errorMessage.getText();
        System.out.println(actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains("Username or password is incorrect, please correct them and try again"));

        Driver.closeDriver();
        //        Test Data:
        //        Url: http://www.carettahotel.com/Account/Logon
        //        user: manager123
        //        pw: Manage!
    }


    }

