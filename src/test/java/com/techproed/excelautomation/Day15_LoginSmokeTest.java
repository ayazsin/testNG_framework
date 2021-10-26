package com.techproed.excelautomation;

import com.techproed.pages.DefaultPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ExcelUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Day15_LoginSmokeTest {

    //creating ExcelUtil object
    ExcelUtil excelUtil;

    //Get the data (username,password) as key-value pairs
    //I will use testData to store username-password values
    //Map<String, String>      : {manager,	Manager1!}
    //List<Map<String,String>> : {{manager,	Manager1!},{manager2,	Manager2!},{manager3	Manager3!}}
    List<Map<String, String>> testData;
    //page objects
    LoginPage loginPage;
    DefaultPage defaultPage;

    public void setUp() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("app_qa_environment"));
        loginPage = new LoginPage();
        Thread.sleep(1000);
//        if (loginPage.advancedLink.isDisplayed()){
            try {
                Thread.sleep(1000);
                loginPage.advancedLink.click();
                Thread.sleep(1000);
                loginPage.proceedLink.click();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Advanced Link and Proceed Link is not displayed");
            }
//        }
    }
    //        setUp is used to log the application page
    //        setUp();
    //        loginPage.username.sendKeys("manager");
    //        loginPage.password.sendKeys("Manager1!");
    //        loginPage.loginButton.click();

    @Test
    public void adminLoginTest() throws InterruptedException {
        String path ="./src/test/java/resources/smoketestdata.xlsx";
        String sheetName = "admin_login_info";
        excelUtil = new ExcelUtil(path, sheetName);

        testData = excelUtil.getDataList();
        System.out.println();//[{password=Techproed123!, username=admin}]

        for (Map<String, String> eachData : testData) {//eachData represent each username-password pairs
            setUp();//login in each loop
            loginPage.username.sendKeys(eachData.get("username"));//admin
            loginPage.password.sendKeys(eachData.get("password"));//Techproed123!
            loginPage.loginButton.click();
        }
    }

    @Test
    public void managerLoginTest() throws InterruptedException {

        String path ="./src/test/java/resources/smoketestdata.xlsx";
        String sheetName="manager_login_info";
        excelUtil= new ExcelUtil(path,sheetName);

        testData=excelUtil.getDataList();
        System.out.println(testData);//


        for(Map<String,String> eachData : testData ){//eachData represent each username-password pairs
            setUp();//login in each loop
            loginPage.username.sendKeys(eachData.get("username"));
            loginPage.password.sendKeys(eachData.get("password"));
            loginPage.loginButton.click();
        }
    }

    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }


}
