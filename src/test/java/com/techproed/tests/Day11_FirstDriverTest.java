package com.techproed.tests;

import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day11_FirstDriverTest {

    @Test
    public void amazonTitleTest () {

        /*driver   -> Driver.getDriver()*/
        Driver.getDriver().get("https://www.amazon.com");

        //getting the title
        String actualTitle = Driver.getDriver().getTitle();

        //Doing the assertion
        Assert.assertTrue(actualTitle.contains("Amazon"));


    }
}
