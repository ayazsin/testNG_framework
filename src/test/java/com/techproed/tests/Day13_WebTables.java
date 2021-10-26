package com.techproed.tests;

import com.techproed.pages.DefaultPage;
import com.techproed.pages.HotelRoomsPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Day13_WebTables {

    /*
            How to write xpath from table elements
                TABLE
                //table//tbody -> to create table body. returns entire table
                //table//tbody//tr -> returns all rows in tbody
                //table//tbody//tr[1] -> first row in tbody
                //table//tbody//tr[1]//td -> returns all table cells on the first row
                //table//tbody//tr[1]//td[4] -> returns 4th cell on the first row in tbody
                //table//tbody//tr[4]//td[5] -> 4th row 5th column
                //table//tbody//tr[10]//td[2] -> row 10 cell 2

                NO tc in web tables. We have tr or td. We use combination of tr and td to fo certain column
                //table//tbody//tr//td[5] -> all rows in column 5
     */

    /*
        Create a class: WebTables
        Create a method: login()
        Log in https://www.carettahotel.com/
        //Click on Hotel Management
        //Click on Hotel Rooms
        Create a test method: entireTable() and Find the size of the entire table body and print all of headers
        Create a test method: printRows() and Print all of the rows and print the element s on the 4th row
        Create a test method: printCells() and a the total number of cells in the table body and print all of the cells
        Create a test method: printColumns() and print Find the total number of columns and Print the elements of the 5th column
        Create a test method: printData(int row, int column); This method should print the given cell. Example: printData(2,3); should print 2nd row,3rd column
     */
    LoginPage loginPage;
    DefaultPage defaultPage;
    HotelRoomsPage hotelRoomsPage;

    //When user goes to HotelRoom page on the application
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
        //Click on Hotel Management
        defaultPage.hotelManagementTab.click();
        //Click on Hotel Rooms
        defaultPage.hotelRoomsTab.click();
        //Click on Add Hotel Room
        hotelRoomsPage = new HotelRoomsPage();
    }

    @Test
    public void entireTable() {

        //Create a test method: entireTable() and Find the size of the entire table body
        //                      and print all of headers

        System.out.println("*Entire table*");
        System.out.println("*Table Body*");
        WebElement tableBody = Driver.getDriver().findElement(By.xpath("//table//tbody"));
        System.out.println(tableBody.getText());

        List<WebElement> allHeaders = Driver.getDriver().findElements(By.xpath("//th"));
        allHeaders.stream().map(t->t.getText()).forEach(System.out::println);

    }

    @Test
    public void printRows() {

        // Create a test method: printRows() and Print all of the rows and print the
        //                          element s on the 4th row

        System.out.println("*Print Rows*");
        List<WebElement> allRows = Driver.getDriver().findElements(By.xpath("//tbody//tr"));
        //row numbers
        int rowNum =1;
        for (WebElement eachRow : allRows) {
            System.out.println("Row Number " + rowNum + " => "+eachRow.getText());
            rowNum++;
        }

        //Print row4 only
        WebElement row4 = Driver.getDriver().findElement(By.xpath("//tbody//tr[4]"));
        System.out.println("ROW 4 => " + row4.getText());

    }

    @Test
    public void printCells() {
       // Create a test method: printCells() and a the total number of cells in the
       //                      table body and print all of the cells

        System.out.println("*Print Cells*");
        List <WebElement> allCells = Driver.getDriver().findElements(By.xpath("//tbody//td"));
        System.out.println("Total Cell Number =>" + allCells.size());

        int cellNum = 1;
        for (WebElement eachCell: allCells) {
            System.out.println(cellNum + ": " +eachCell.getText());
            cellNum++;
        }
    }

    @Test
    public void printColumns() {
        //Create a test method: printColumns() and print Find the total number of columns


        List<WebElement> numberOfColumns = Driver.getDriver().findElements(By.xpath("//th"));
        System.out.println("Number of Columns is " + numberOfColumns.size());

        //                      and Print the elements of the 5th column
        List<WebElement> columnOf5 = Driver.getDriver().findElements(By.xpath("//td[5]"));

        int colnumb = 1;
        for (WebElement eachCell: columnOf5) {
            System.out.println(colnumb + ": " + eachCell.getText());
            colnumb++;
        }

    }

    public void printData(int row, int column) {
        //Create a test method: printData(int row, int column);
        // This method should print the given cell.
        // Example: printData(2,3); should print 2nd row,3rd column

        WebElement actualData = Driver.getDriver().findElement(By.xpath("//table//tbody//tr["+row+"]//td["+column+"]"));
        System.out.println("The data of " + row + ". row and " + column + ". column is " + actualData.getText());
    }

    @Test
    public void testTableData() {

        printData(7,2);//The data of 7. row and 2. column is Cizgi Dusler Hotel
        printData(2,3);//The data of 2. row and 3. column is 111
        printData(5,5);//The data of 5. row and 5. column is aaa

    }
    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}
