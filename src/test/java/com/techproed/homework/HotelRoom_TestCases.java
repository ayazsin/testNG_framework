package com.techproed.homework;

import com.techproed.pages.HotelListPage;
import com.techproed.pages.LoginPage;
import com.techproed.pages.TestAddressLoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HotelRoom_TestCases {

    HotelListPage hotelListPage;
    Select idGroup;

    @BeforeMethod
    public void setUp() {
        //Going to page
        Driver.getDriver().get(ConfigReader.getProperty("app_carettahotel_login_url"));
        LoginPage loginPage = new LoginPage();
        //Entering username and password
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();

        //Going to hotel list
        hotelListPage = new HotelListPage();
        hotelListPage.hotelManagement.click();
        hotelListPage.hotelList.click();
    }

    @Test(priority = 1)
    public void hotelType1() throws InterruptedException {

        //Selecting Hotel Type1
        idGroup = new Select(hotelListPage.idGroup);
        idGroup.selectByVisibleText("Hotel Type1");
        hotelListPage.search.click();

        //If the all IDGroup is "Hotel Type1"

        Thread.sleep(3000);
        List <WebElement> idGroupColumn = Driver.getDriver().findElements(By.xpath("//table//tbody//td[7]"));

        boolean isAllCellHotelType1 = true;
        for (WebElement eachCell : idGroupColumn) {
            System.out.println(eachCell.getText());
            if (!eachCell.getText().equals("Hotel Type1")) {
                isAllCellHotelType1 = false;
                break;
            }
        }
        Assert.assertTrue(isAllCellHotelType1);
    }

    @Test(priority = 2)
    public void hotelType2() throws InterruptedException {

        //Selecting Hotel Type2
        idGroup = new Select(hotelListPage.idGroup);
        idGroup.selectByVisibleText("Hotel Type2");
        hotelListPage.search.click();

        //If the all IDGroup is "Hotel Type2"

        Thread.sleep(3000);

        List <WebElement> idGroupColumn2 = Driver.getDriver().findElements(By.xpath("//table//tbody//td[7]"));

        boolean isAllCellHotelType2 = true;
        for (WebElement eachCell : idGroupColumn2) {
            System.out.println(eachCell.getText());
            if (!eachCell.getText().equals("Hotel Type2")) {
                isAllCellHotelType2 = false;
                break;
            }
        }
        Assert.assertTrue(isAllCellHotelType2);
    }

    @Test(priority = 3)
    public void clearTest() throws InterruptedException {
        //Click Clear button
        hotelListPage.clear.click();
        Thread.sleep(3000);
        List<WebElement> columnIdGroup = Driver.getDriver().findElements(By.xpath("//table//tbody//td[7]"));

        //Transferring the to string list
        List <String> columnIdGroupList = new ArrayList<>();
        for (WebElement eachCell : columnIdGroup) {
            columnIdGroupList.add(eachCell.getText());
        }

        //Transferring to string container
        String columnIdGroupString = columnIdGroupList.toString();
        System.out.println(columnIdGroupString);

        boolean isContainBoth = true;

        //Checking if the column contain Type1 and Type2
        if(!columnIdGroupString.contains("Hotel Type1") && !columnIdGroupString.contains("Hotel Type2")) {
            isContainBoth = false;
        }
        Assert.assertTrue(isContainBoth);
    }

    @Test(priority = 4)
    public void testingCurrentDate() {

        //Finding current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        String expectedDate = dtf.format(now);
        System.out.println(dtf.format(now));

        String actualDate = hotelListPage.currentDate.getText();
        //Asserting with the actual date
        Assert.assertEquals(expectedDate,actualDate);

    }

    @Test(priority = 5)
    public void recordsTest() throws InterruptedException {
        //Check if the number of records is 10
        Select records = new Select(hotelListPage.records);
        Assert.assertEquals("10",records.getFirstSelectedOption().getText());

        //Select 20 and verify if there are 20 records
        records.selectByVisibleText("20");
        Thread.sleep(3000);
        List<WebElement> numberOfRecords = Driver.getDriver().findElements(By.xpath("//table//tbody//td[7]"));
        Assert.assertEquals(numberOfRecords.size(),20);

        //Go to 2 page and verify if it is on the second page
        hotelListPage.next.click();
        Thread.sleep(1000);
        String actualPage = hotelListPage.currentPage.getAttribute("value");
        Assert.assertEquals(actualPage,"2");
    }

    @Test(priority = 6)
    public void exportTest() throws InterruptedException {
        hotelListPage.export.click();
        Thread.sleep(3000);
        File downloadedFile = new File("/Users/ayse/Downloads/Admin - List Of Hotels.xlsx");
        Assert.assertTrue(downloadedFile.exists());

    }

    @Test(priority = 7)
    public void detailTest() throws InterruptedException {
        //If the caption is "Edit Hotel"
        hotelListPage.firstDetail.click();
        ArrayList<String> wid = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(wid.get(1));

        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(hotelListPage.captionEdit.getText(),"Edit Hotel");

        hotelListPage.code.clear();
        hotelListPage.code.sendKeys("10159");
        hotelListPage.save.click();
        Thread.sleep(3000);
        String actualMessage = hotelListPage.successMessage.getText();
        Assert.assertEquals(actualMessage,"Hotel was updated successfully");
        hotelListPage.ok.click();

        //Go to Properties
        hotelListPage.propertiesTab.click();
        hotelListPage.barcode.clear();
        hotelListPage.barcode.sendKeys("123456");
        hotelListPage.update.click();

        Thread.sleep(1000);
        Assert.assertEquals(hotelListPage.valueUpdatedMessage.getText(),"Value was updated Succesfully");
        hotelListPage.ok.click();

        hotelListPage.delete.click();
        hotelListPage.confirm.click();

        Thread.sleep(3000);
        String actualErrorMessage = hotelListPage.errorMessage.getText();


        Assert.assertEquals(actualErrorMessage,"Error: Couldn't delete hotel : please delete reservations for this hotel first");
        hotelListPage.ok.click();


    }



    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}
