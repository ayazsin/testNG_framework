package com.techproed.tests;

import com.techproed.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Day10_Hard_Assertion {

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
        driver.close();
    }

    @Test
    public void hardAssert() {
        /*
         * when user goes to the application home page
         * then verifies the title equals 'Caretta Hotel'
         * and clicks on login button
         * then verify the page title equals Caretta Hotel - Log in
         */

        driver.get("http://www.carettahotel.com/");
        //using hard assertion

        Assert.assertTrue(driver.getTitle().equals("Caretta Hotel"));//FAILED. STOPPED
        //NOTE: Line 39 failed so the rest of the test case will NOT execute

        //clicking on login button
        driver.findElement(By.linkText("Log in")).click();
        Assert.assertTrue(driver.getTitle().equals("Caretta Hotel - Log in"));

    }





}
