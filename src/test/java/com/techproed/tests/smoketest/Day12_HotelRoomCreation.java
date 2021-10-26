package com.techproed.tests.smoketest;

import com.github.javafaker.Faker;
import com.techproed.pages.DefaultPage;
import com.techproed.pages.HotelRoomsPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day12_HotelRoomCreation {

    LoginPage loginPage;
    DefaultPage defaultPage;
    HotelRoomsPage hotelRoomsPage;

    @BeforeMethod
    public void setUp() {
        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
        loginPage = new LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();
        //asserting login success
        defaultPage = new DefaultPage();
        Assert.assertTrue(defaultPage.addUserButton.isDisplayed());

    }

    @Test
    public void hotelRoomCreate() throws InterruptedException {

        //Click on Hotel Management
        defaultPage.hotelManagementTab.click();
        //Click on Hotel Rooms
        defaultPage.hotelRoomsTab.click();
        //Click on Add Hotel Room
        hotelRoomsPage = new HotelRoomsPage();
        hotelRoomsPage.addHotelRoomLink.click();

        //Enter All required fields
        //ID IS DROPDOWN
        Select select = new Select(hotelRoomsPage.idDropdown);
        select.selectByIndex(2);

        //Code
        hotelRoomsPage.code.sendKeys("discount code");

        //Name
        hotelRoomsPage.name.sendKeys("Royal King Room");

        //Location
        hotelRoomsPage.location.sendKeys("Royal King Palace in Downtown - New York");

        //Description
        hotelRoomsPage.description.sendKeys("This is the best room in the hotel");

        //Price
        //1.way:
//        hotelRoomsPage.price.sendKeys("400");

        //2.way:
        Actions actions = new Actions(Driver.getDriver());
        Thread.sleep(1000);
        actions.dragAndDrop(hotelRoomsPage.price700,hotelRoomsPage.price).build().perform();

        //Room Type
        Select roomType = new Select(hotelRoomsPage.roomType);
        roomType.selectByVisibleText("Double");

        //Max Adult count
        hotelRoomsPage.maxAdultCount.sendKeys("2");

        //Max Children count
        hotelRoomsPage.maxChildCount.sendKeys("2");

        //Approved
        hotelRoomsPage.approved.click();

        //Click Save
        hotelRoomsPage.save.click();


        //Assertion fails cause window pop up takes some time to open-less than a sec
        //Synchronization issue

        //WAY 1 - Thread.sleep(3000); - not recommended
        //WAY 2 - Explicit Wait

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        WebElement popupMessage = wait.until(ExpectedConditions.visibilityOf(hotelRoomsPage.message));

        //Verify message
        Assert.assertEquals(hotelRoomsPage.message.getText(),"HotelRoom was inserted successfully");

        //Click Ok.
        hotelRoomsPage.ok.click();

    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }

}
