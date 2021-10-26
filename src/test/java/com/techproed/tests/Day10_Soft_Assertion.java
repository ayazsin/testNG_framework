package com.techproed.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class Day10_Soft_Assertion {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void tearDown(){
//        driver.close();
    }

    @Test
    public void softAssert() {
        /*
         * when user goes to the application home page
         * then verifies the title equals 'Caretta Hotel'
         * and clicks on login button
         * then verify the page title equals Caretta Hotel - Log in
         *
         * */

        driver.get("http://www.carettahotel.com/");

        /*Step 1 : Create SoftAssert object */
        SoftAssert softAssert = new SoftAssert();

        /*Step 2 : Do assertion using softAssert object*/
        softAssert.assertTrue(driver.getTitle().equals("Caretta Hotel"));

        //Line 45 fails. BUT THE TEST CONTINUE TO RUN. BECAUSE THIS IS SOFT ASSERTION.

        //Clicking on Login button
        driver.findElement(By.linkText("Log in")).click();
        //asserting if the new page title is as expected
        softAssert.assertTrue(driver.getTitle().equals("Caretta Hotel - Log in"));

        /*!!! use assertAll() to make the actual assertion.
         * If you do not use assertAll, you get false test result*/
        softAssert.assertAll();


    }
}
